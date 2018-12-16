package com.aqryuz.footballTicketDemo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.and()
		.formLogin();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}admin")
		.roles("ADMIN");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/img/**");
		web.ignoring().antMatchers("/api/**");
		web.ignoring().antMatchers("/favicon.ico");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
        .allowedHeaders("Content-Type", "Date", "Total-Count", "loginInfo","jwt_token")
        .exposedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "jwt_token")
        .maxAge(3600);
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
}