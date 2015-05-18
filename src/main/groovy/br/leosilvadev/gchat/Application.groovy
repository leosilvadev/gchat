package br.leosilvadev.gchat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper

@SpringBootApplication
class Application {
	
	static void main(String[] args) throws Exception {
		configurePort()
		configureDatabase()
		
		SpringApplication.run(Application.class, args)
	}

	private static configureDatabase() {
		if ( System.getenv().DATABASE_URL ) {
			URI dbUri = new URI(System.getenv().DATABASE_URL)
			String username = dbUri.getUserInfo().split(":")[0]
			String password = dbUri.getUserInfo().split(":")[1]
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
			
			System.setProperty("spring.datasource.url", dbUrl)
			System.setProperty("spring.datasource.username", username)
			System.setProperty("spring.datasource.username", password)
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
		extendObjects(jsonMapper)
	}
	
	def extendObjects(jsonMapper){
		Object.metaClass.toJson {
			jsonMapper.writeValueAsString(delegate)
		}
		String.metaClass.toObject { Class<?> clazz ->
			jsonMapper.readValue(delegate, clazz)
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
		new ObjectMapper();
	}
	
}
