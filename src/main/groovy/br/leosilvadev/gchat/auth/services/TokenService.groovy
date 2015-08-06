package br.leosilvadev.gchat.auth.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis
import br.leosilvadev.gchat.databases.managers.RedisManager
import br.leosilvadev.gchat.exceptions.InvalidTokenException
import br.leosilvadev.gchat.model.services.UserService
import br.leosilvadev.gchat.repositories.UserRepository;

@Service
class TokenService {

	@Autowired UserRepository userRepository
	@Autowired RedisManager redisManager
	
	@Value('${gchat.auth.token.expiretime.seconds}')
	private Integer tokenExpireTime
	
	def validate(String token, Closure onSuccess, Closure onFailure){
		if(!token) return onFailure(new InvalidTokenException("Invalid request"))
		
		def id = redisManager.execute({ Jedis jedis -> 
			jedis.get(token)
		})
		
		if(!id) return onFailure(new InvalidTokenException("Invalid request"))
		
		def user = userRepository.findOne(id.toLong())
	
		if(!user) return onFailure(new InvalidTokenException("Invalid request"))
		
		onSuccess(user)
	}
	
	def renew(String token){
		redisManager.execute({ Jedis jedis -> 
			jedis.expire token, tokenExpireTime
		})
	}
	
}
