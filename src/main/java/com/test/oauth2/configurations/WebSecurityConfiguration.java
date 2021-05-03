package com.test.oauth2.configurations;

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

import com.test.oauth2.constants.Roles;
import com.test.oauth2.constants.Users;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username(Users.USER.getUserName())
				.password(passwordEncoder.encode(Users.USER.getPassword())).roles(Roles.USER.getRole()).build();

		UserDetails userAdmin = User.builder().username(Users.ADMIN.getUserName())
				.password(passwordEncoder.encode(Users.ADMIN.getPassword()))
				.roles(Roles.USER.getRole(), Roles.ADMIN.getRole()).build();

		return new InMemoryUserDetailsManager(user, userAdmin);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
