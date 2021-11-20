package com.misiontic2022.hackaton.webreciclaje.webreciclaje.security;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======

>>>>>>> a54a53acd495b998b75c059b34c120e724bc6d22
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.UsuarioSecurity;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class MainSecurity extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD
	@Autowired
	UserDetailsServiceImp userDetailsService;

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**",
			"/documents/**", "/vendor/**" };

=======
	String[] resources = new String[]{
           "/include/**","/css/**","/icons/**","/images/**","/js/**","/layer/**", "/documents/**",
           "/vendor/**"
    };
	
>>>>>>> a54a53acd495b998b75c059b34c120e724bc6d22
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImp();		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/swagger-ui.html/**").permitAll().antMatchers("/**").permitAll()
				.antMatchers("/api/").permitAll()
<<<<<<< HEAD
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
=======
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.usernameParameter("username")
					.defaultSuccessUrl("/dashboard")
				.and()
				.logout().logoutSuccessUrl("/").permitAll();
		}
>>>>>>> a54a53acd495b998b75c059b34c120e724bc6d22

}