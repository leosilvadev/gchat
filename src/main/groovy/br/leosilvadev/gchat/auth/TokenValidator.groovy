package br.leosilvadev.gchat.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import redis.clients.jedis.Jedis
import br.leosilvadev.gchat.databases.managers.RedisManager
import br.leosilvadev.gchat.exceptions.InvalidTokenException
import br.leosilvadev.gchat.model.services.UserService
import br.leosilvadev.gchat.repositories.UserRepository;

@Component
class TokenValidator {

	@Autowired UserRepository userRepository
	@Autowired RedisManager redisManager
	
	def validate(token, Closure onSuccess, Closure onFailure){
		if(!token) return onFailure(new InvalidTokenException("Invalid request"))
		
		def id = redisManager.execute({ Jedis jedis -> 
			jedis.get(token)
		})
		
		if(!id) return onFailure(new InvalidTokenException("Invalid request"))
		
		def user = userRepository.findOne(id.toLong())
	
		if(!user) return onFailure(new InvalidTokenException("Invalid request"))
		
		onSuccess(user)
	}
	
}
