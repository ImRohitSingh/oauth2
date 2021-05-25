package com.test.oauth2.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.test.oauth2.constants.Roles;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/public")
				.permitAll();

		http.requestMatchers().mvcMatchers("/deployment").and().authorizeRequests()
				.mvcMatchers("/deployment").permitAll();

		http.csrf().disable().requestMatchers().antMatchers("/private/**").and().authorizeRequests()
				.antMatchers("/private/**").hasRole(Roles.USER.getRole());

		http.csrf().disable().requestMatchers().antMatchers("/admin**").and().authorizeRequests()
				.antMatchers("/admin**").hasRole(Roles.ADMIN.getRole());

	}

}
