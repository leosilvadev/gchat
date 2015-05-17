package br.leosilvadev.gchat.databases.managers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import redis.clients.jedis.JedisPool

@Component
class RedisManager {
	
	@Autowired JedisPool jedisPool
	
	def execute(function, errorCallback=null){
		try {
			def jedis = jedisPool.resource
			function jedis
			
		} catch (e) {
			if ( errorCallback ) errorCallback e
		}
	}

}
