package com.example.secureApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// http basic example
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().logout().logoutUrl("/logout")
				.invalidateHttpSession(true).and().httpBasic();
	}

	/*
	 * //form based login example protected void configure(HttpSecurity http) throws
	 * Exception {
	 * http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().
	 * anyRequest().authenticated().and()
	 * .formLogin().loginPage("/login").permitAll().and().logout().
	 * invalidateHttpSession(true)
	 * .clearAuthentication(true).logoutRequestMatcher(new
	 * AntPathRequestMatcher("/logout"))
	 * .logoutSuccessUrl("/logout-success").permitAll(); }
	 */

	/*
	 * //this for the simple in built memory details service
	 * 
	 * @Bean public UserDetailsService userDetailsService() {
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(
	 * User.withDefaultPasswordEncoder().username("user").password("password").roles
	 * ("USER").build()); return manager; }
	 */

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

		return provider;

	}

}
