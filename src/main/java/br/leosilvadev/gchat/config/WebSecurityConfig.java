package br.leosilvadev.gchat.config;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.leosilvadev.gchat.model.domain.User;
import br.leosilvadev.gchat.repositories.UserRepository;
import br.leosilvadev.gchat.security.UserSecurity;

@Configuration
@EnableWebMvcSecurity
@EnableAsync
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserRepository userRepository;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/auth/**", "/authenticate", "/logout", "/assets/**", "/templates/**", "/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                
            .formLogin()
            	.loginProcessingUrl("/authenticate")
                .loginPage("/")
                .defaultSuccessUrl("/home")
                .failureHandler((request, response, exception) -> {
                	if( exception instanceof LockedException ) {
                		response.sendRedirect("#locked");
                	} else {
                		response.sendRedirect("#error");
                	}
                })
                .and()
                
            .logout()
            	.logoutUrl("/logout")
            	.logoutSuccessUrl("/")
                .and()
                
            .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService((email) -> {
            	User user = userRepository.findOneByEmail(email);
            	
            	if(user==null) throw new UsernameNotFoundException(email + " not found");
            	
            	String code = UUID.randomUUID().toString();
				return new UserSecurity(user, code);
				
            }).passwordEncoder(encoder());
    }
    
	@Bean
	public ShaPasswordEncoder encoder(){
		return buildEncoder();
	}
	
	public static ShaPasswordEncoder buildEncoder(){
		return new ShaPasswordEncoder(256);
	}
    
}