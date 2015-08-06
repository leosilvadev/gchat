package br.leosilvadev.gchat.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import redis.clients.jedis.Jedis
import br.leosilvadev.gchat.databases.managers.RedisManager
import br.leosilvadev.gchat.exceptions.AuthTokenGenerationException
import br.leosilvadev.gchat.exceptions.NoAuthenticatedException
import br.leosilvadev.gchat.mail.dto.AuthenticationRequest
import br.leosilvadev.gchat.model.services.UserService

@Component
class Authenticator {
	
	@Autowired UserService userService
	@Autowired RedisManager redisManager
	
	@Value('${gchat.auth.token.expiretime.seconds}')
	private Integer tokenExpireTime
	
	def authenticate(username, password, onSuccess, onFailure){
		def user = userService.findByUsernameAndPassword(username, password)
		def authentication = new AuthenticationRequest(username, password)
		
		if(!user) onFailure(new NoAuthenticatedException("Invalid authentication"))
		
		def authenticated = authentication.authenticated()
		
		redisManager.execute({ Jedis jedis ->
			jedis.setex(authenticated.token, tokenExpireTime, user.id.toString())
			onSuccess(authenticated)
			
		}, { ex ->
			onFailure(new AuthTokenGenerationException("We had some troubles to authenticate you, try again later", ex))
		
		})
		
	}
	
}
