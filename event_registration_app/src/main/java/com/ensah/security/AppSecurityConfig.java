package com.ensah.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	// Configure AuthenticationProvider
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(getUserDetailsService());
		// define the hash algorithm of the password
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	// Configure AuthenticationSuccessHandler
	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

	// Configure AuthenticationFailureHandler
	@Bean
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	// Configure UserDetailsService
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomDaoBasedUserDetailsService();
	}

	// Configure access permissions and spring security features
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/user/**").hasRole("USER") // The USER role accesses requests starting
																			// with /user/
				.antMatchers("/admin/**").hasRole("ADMIN") // The ADMIN role accesses requests starting with /admin/
				.antMatchers("/adminuser/**").hasAnyRole("ADMIN", "USER") // available for USER and ADMIN roles
				.anyRequest().permitAll() // without authentication
				.and().formLogin().loginPage("/showMyLoginPage") // Indicate the mapping displaying the login form
				.loginProcessingUrl("/authenticateTheUser") // Same value to put in the action attribute in the login
															// form. This will redirect to the right Spring Security
															// filter that handles authentication
				.failureHandler(getAuthenticationFailureHandler())// Manager of failure authentication
				.successHandler(getAuthenticationSuccessHandler())// Manager of success authentication
				.and().logout()// We used the default value. You can customize the disconnection with
								// .logoutUrl("/perform_logout") with /perform_logout is the mapping the Spring
								// method to be executed at disconnection
				.deleteCookies("JSESSIONID") // delete the session cookie after logging out
				.and().exceptionHandling().accessDeniedPage("/access-denied");// Specify the mapping that Spring will
																				// use to redirect to the unauthorized
																				// access page

	}

}
