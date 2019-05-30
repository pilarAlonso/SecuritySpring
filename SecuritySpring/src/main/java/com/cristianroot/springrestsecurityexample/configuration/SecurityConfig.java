/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.configuration;

import com.cristianroot.springrestsecurityexample.components.AuthenticationTokenFilter;
import com.cristianroot.springrestsecurityexample.constants.properties.JwtProperties;
import com.cristianroot.springrestsecurityexample.services.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//esto ativa que se pueda limitar por roles
@EnableGlobalMethodSecurity(prePostEnabled = true)
//los objetos necesaarios para que spring funcione
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final JwtProperties jwtProperties;

	public SecurityConfig(CustomUserDetailsServiceImpl userDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint, JwtProperties jwtProperties) {
		this.userDetailsService = userDetailsService;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.jwtProperties = jwtProperties;
	}
//disponer el uso de autentication para el resto de clases
	//antes era un bean por defecto
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
//encriptar ponerlo a disposiocion de las clases para inyetarse
	//spring va avenir a recoger ese objeto aqui cada vez que se necesite crear para esto es la anotacion bean y solo lo re una vez porqu elo tiene en memoria de la primera vez que se a pedido
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//pra que utilice paswor encoder y autentiation manager
	//
	//hace que use mi implementacion y no la implementaion por defecto
	@Autowired
	//configura user detailservce y password encoder
	public void configureAuthenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
//confidduramos toda la seguridd
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()//filtro de navegadores restricciones para comprobar el origen de la peticion, hay que cnnfigurarlo , aqui tiene la configuracion basica
			.csrf().disable()//filtro que hay que deshabilitar porque no es seguro
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()//no quiero que cree sesiones
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//filtro que autentica que el token est abien
			.addFilter(new AuthenticationTokenFilter(jwtProperties, authenticationManagerBean()))
			.authorizeRequests()//autentiaionbasica
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers("/auth/login").permitAll()//odne permito la autentiacion
			.anyRequest().permitAll();
	}

}
