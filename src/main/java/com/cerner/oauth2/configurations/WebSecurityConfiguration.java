package com.cerner.oauth2.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails pubUser = User.builder().username("public").password(passwordEncoder.encode("publicpass")).roles("USER")
				.build();
		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("userpass")).roles("USER")
				.build();
		UserDetails userAdmin = User.builder().username("admin").password(passwordEncoder.encode("adminpass"))
				.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(pubUser, user, userAdmin);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
