package br.leosilvadev.gchat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter
import org.springframework.security.authentication.encoding.ShaPasswordEncoder
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper

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
		ObjectMapper jsonMapper = new ObjectMapper()
		jsonMapper.setSerializationInclusion(Include.NON_NULL)
		jsonMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		
		def encoder = new ShaPasswordEncoder(256)
		
		extendObjects(jsonMapper, encoder)
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

	@Bean LocalValidatorFactoryBean validator(){
		new LocalValidatorFactoryBean();
	}
	
	@Bean FilterRegistrationBean openEntityManagerViewInFilter(){
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new OpenEntityManagerInViewFilter());
		filterRegistration.addUrlPatterns("/*");
		filterRegistration;
	}
	
	@Bean ObjectMapper jsonMapper(){
		def mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL)
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		mapper
	}
	
}
