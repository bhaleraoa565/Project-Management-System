package com.Product.Management.System.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
	            .requestMatchers("/register").permitAll()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/Products/**").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/Products", true)
                .permitAll()
        )
        .logout(logout -> 
            logout
                .permitAll()
        );


    return http.build();
}

@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.builder()
        .username("user")
        .password("{noop}password") // {noop} indicates no password encoder is used
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
}
	    
	  
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	    

	 
}
