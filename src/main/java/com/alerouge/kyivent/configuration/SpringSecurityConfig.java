package com.alerouge.kyivent.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}


	@Autowired
	private DataSource dataSource;

	//Enable jdbc authentication
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(
				"SELECT email, password, enabled from users where email = ?")
		.authoritiesByUsernameQuery(
				"SELECT u.email, a.authority " +
						"FROM user_authorities a, users u " +
						"WHERE u.email = ? " +
						"AND u.id = a.user_id"
				);
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/images/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/", "/home", "/login", "/firstPage").permitAll()
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.successHandler(myAuthenticationSuccessHandler)
		.permitAll()
		.and()
		.logout()
	        .logoutSuccessHandler(myLogoutSuccessHandler)
	        .invalidateHttpSession(false)
	        .logoutSuccessUrl("/logout.html?logSucc=true")
	        .deleteCookies("JSESSIONID")
			.permitAll()
		.and()
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

		http.csrf().disable();
	}

}