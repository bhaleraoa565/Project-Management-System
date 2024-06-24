package com.Product.Management.System.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.Product.Management.System.Service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

	 private final UserService userService;

	    public SecurityConfiguration(UserService userService) {
	        this.userService = userService;
	       
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
	            )
	            .formLogin(formLogin ->
	                formLogin.loginPage("/auth/login").permitAll()
	            );

	        return http.build();
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        return userService;
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	    

	 
}
