package com.sahani.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sahani.student.security.JwtAuthenticationEntryPoint;
import com.sahani.student.security.JwtTokenProvider;

@Configuration
public class SpringConfig {
	
	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JwtTokenProvider jwtFilter;

	public SpringConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			JwtTokenProvider jwtFilter) {
		
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtFilter = jwtFilter;
	}



	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize)->{
                   // authorize.requestMatchers("std/**").permitAll();

                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
       http .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint));

    //  http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    
        return http.build();
    }
	
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password(passwordEncoder().encode("john"))
//                .roles("USER")
//                .build();
//
//        UserDetails sam = User.builder()
//                .username("sam")
//                .password(passwordEncoder().encode("sam"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,sam);
//    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 
	
}
