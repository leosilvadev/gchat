package br.leosilvadev.gchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.leosilvadev.gchat.model.domain.User;
import br.leosilvadev.gchat.repositories.UserRepository;import br.leosilvadev.gchat.security.UserSecurity;


@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserRepository userRepository;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/authenticate", "/logout", "/resources/**", "/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                
            .formLogin()
            	.loginProcessingUrl("/authenticate")
                .loginPage("/")
                .defaultSuccessUrl("/home")
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
				return new UserSecurity(user);
				
            }).passwordEncoder(encoder());
    }
    
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
    
}