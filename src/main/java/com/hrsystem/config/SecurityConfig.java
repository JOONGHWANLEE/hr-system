package com.hrsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/fonts/**", "/libs/**").permitAll()
//				.requestMatchers("/", "/users/**", "/boards/**", "/calendar/**", "/commute/**").permitAll()
				.requestMatchers("/users/login", "/users/new").permitAll()
				.requestMatchers("/favicon.ico", "error").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
			.formLogin(formLogin -> formLogin
					.loginPage("/users/login")
					.defaultSuccessUrl("/")
					.usernameParameter("no")
					.failureUrl("/users/login/error")
					)
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
					.logoutSuccessUrl("/users/login")
					)
//			.exceptionHandling(handling -> handling
//					.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//					)
			.rememberMe(Customizer.withDefaults());
		
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
