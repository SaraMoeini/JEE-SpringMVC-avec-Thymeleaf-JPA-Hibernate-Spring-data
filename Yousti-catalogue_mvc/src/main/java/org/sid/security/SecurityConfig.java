package org.sid.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//@Autowired
	//private DataSource dataSource;
	//SOLUTION1 IN MEMORY AUTHENTICATION
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}12345").roles("USER","ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
	}
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.authoritiesByUsernameQuery("SELECT login AS principal,pass AS credentials,active FROM users WHERE login=?")
			.authoritiesByUsernameQuery("SELECT login AS principal, role AS role FROM users_roles WHERE login=?")
			.passwordEncoder(new Md4PasswordEncoder())
			.rolePrefix("ROLE_");
	}
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		//http.formLogin().loginPage("/login");
		http.formLogin();
		http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
	//SOLUTION2  IN MEMORY AUTHENTICATION
	/*@Bean
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("1234").roles("USER").build());
        manager.createUser(users.username("admin").password("12345").roles("USER", "ADMIN").build());
        return manager;

    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		http.formLogin();
		http.authorizeRequests()
		.antMatchers("/index").access("hasRole('USER') ")
		.antMatchers("/form","/save","/edit","/delete").access("hasRole('ADMIN')");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	*/
}
