package br.leosilvadev.gchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.leosilvadev.gchat.auth.TokenValidator;
import br.leosilvadev.gchat.events.TokenAuthFilter;
import br.leosilvadev.gchat.repositories.UserRepository;

@Configuration
@EnableWebMvcSecurity
@EnableAsync
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserRepository userRepository;
	@Autowired private TokenValidator tokenValidator;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/auth/**", "/authenticate", "/logout", "/assets/**", "/templates/**", "/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
             
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .formLogin().disable()
            .logout().disable()
            .csrf().disable();
        
        http.addFilterBefore(new TokenAuthFilter(tokenValidator), BasicAuthenticationFilter.class);
    }
    
	@Bean
	public ShaPasswordEncoder encoder(){
		return buildEncoder();
	}
	
	public static ShaPasswordEncoder buildEncoder(){
		return new ShaPasswordEncoder(256);
	}
    
}