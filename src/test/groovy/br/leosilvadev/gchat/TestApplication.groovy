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
class TestApplication {

	static void main(String[] args) throws Exception {
		SpringApplication.run(TestApplication.class, args)
	}
	
	TestApplication(){
		ObjectMapper jsonMapper = new ObjectMapper()
		jsonMapper.setSerializationInclusion(Include.NON_NULL)
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
