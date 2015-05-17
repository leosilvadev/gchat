package br.leosilvadev.gchat.databases.managers

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import spock.lang.Specification

class RedisManagerSpec extends Specification {

	JedisPool pool
	RedisManager manager
	
	def setup(){
		pool = Mock JedisPool
		manager = new RedisManager(jedisPool: pool)
	}
	
	def "A manager must execute a function without an error callback"(){
		def jedis = Mock Jedis
		given: "A function to be executed"
			def function = Mock Closure
			
		when: "The function is passed to manager to execute without an error callback"
			manager.execute function
			
		then: "The function need to be executed"
			pool.getResource() >> jedis
			1 * function(jedis)
	}
	
	def "A manager must not execute a function when do not get an Redis connection"(){
		def ex = new RuntimeException()
		def jedis = Mock Jedis
		given: "A function to be executed"
			def function = Mock Closure
			
		and: "An error callback"
			def callback = Mock Closure
			
		when: "The function is passed to manager to execute with an error callback"
			manager.execute(function, callback)
			
		then: "The function should not be executed"
			pool.getResource() >> { throw ex }
			0 * function(jedis)
			
		and: "The error callback must be called"
			1 * callback(ex)
	}
	
	def "A manager must execute a function and an callback error if the function throws an exception"(){
		def ex = new RuntimeException()
		def jedis = Mock Jedis
		given: "A function to be executed"
			def function = Mock Closure
			
		and: "An error callback"
			def callback = Mock Closure
			
		when: "The function is passed to the manager to be executed with an error callback"
			manager.execute(function, callback)
			
		then: "The function should not be executed"
			pool.getResource() >> jedis
			1 * function(jedis) >> { throw ex }
			
		and: "The error callback must be called"
			1 * callback(ex)
	}
	
}
