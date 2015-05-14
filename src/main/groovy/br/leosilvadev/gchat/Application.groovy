package br.leosilvadev.gchat

import groovy.json.JsonBuilder;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
class Application {
	
	static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args)
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
