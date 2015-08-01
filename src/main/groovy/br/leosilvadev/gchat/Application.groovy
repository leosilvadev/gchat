package br.leosilvadev.gchat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import br.leosilvadev.gchat.config.WebConfig
import br.leosilvadev.gchat.config.WebSecurityConfig

@SpringBootApplication
class Application {
	
	static void main(String[] args) throws Exception {
		configurePort()
		configureDatabase()
		
		SpringApplication.run(Application.class, args)
	}

	public static configureDatabase() {
		def url = System.getenv().HEROKU_POSTGRESQL_GREEN_URL
		if ( url ) {
			URI dbUri = new URI(System.getenv().HEROKU_POSTGRESQL_GREEN_URL)
			String username = dbUri.getUserInfo().split(":")[0]
			String password = dbUri.getUserInfo().split(":")[1]
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
			
			System.setProperty("spring.datasource.url", dbUrl)
			System.setProperty("spring.datasource.username", username)
			System.setProperty("spring.datasource.password", password)
		}
	}

	private static configurePort() {
		if ( System.getenv().PORT ) {
			String webPort = System.getenv().PORT
			System.setProperty("server.port", webPort)
			
		}
	}
	
	Application(){
		extendObjects(WebConfig.buildJsonMapper(), WebSecurityConfig.buildEncoder())
	}
	
	def extendObjects(jsonMapper, encoder){
		Object.metaClass.toJson {
			jsonMapper.writeValueAsString(delegate)
		}
		String.metaClass.toObject { Class<?> clazz ->
			jsonMapper.readValue(delegate, clazz)
		}
		String.metaClass.hash {
			encoder.encodePassword(delegate, delegate)
		}
	}
	
}
