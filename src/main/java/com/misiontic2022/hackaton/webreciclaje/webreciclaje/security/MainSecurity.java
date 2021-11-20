package com.misiontic2022.hackaton.webreciclaje.webreciclaje.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.UserDetailsServiceImp;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.UsuarioRepository;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.security.token.JwtEntryPoint;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.security.token.JwtTokenFIlter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImp userDetailsService;

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	String[] resources = new String[]{
           "/include/**","/css/**","/icons/**","/images/**","/js/**","/layer/**", "/documents/**",
           "/vendor/**"
    };
	
	@Bean
	public JwtTokenFIlter jwtTokenFilter() {
		return new JwtTokenFIlter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers(resources).permitAll()
				.antMatchers("/swagger-ui.html/**").permitAll()
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/api/").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.usernameParameter("username")
					.defaultSuccessUrl("/dashboard")
				.and()
				.logout().logoutSuccessUrl("/").permitAll()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}