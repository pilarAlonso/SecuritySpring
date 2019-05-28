/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.controllers;

import com.cristianroot.springrestsecurityexample.constants.properties.JwtProperties;
import com.cristianroot.springrestsecurityexample.models.AuthenticationRequest;
import com.cristianroot.springrestsecurityexample.models.AuthenticationResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
//crea el token para el firmado vamos a utilizar libreria de terceros JWT.io
@RestController
public class AuthenticationController {
///manager es una interfaz de spring que se enarga de autenticar e implementar metodos para la autentiacion
	private final AuthenticationManager authenticationManager;
	//jwt es un token de acceso
	private final JwtProperties jwtProperties;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
		this.authenticationManager = authenticationManager;
		this.jwtProperties = jwtProperties;
	}
//permitimos el log
	@PostMapping("/auth/login")
	public AuthenticationResponse getToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		// Let spring check the credentials and get the authorities
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
														authenticationRequest.getPassword()));

		// Extract authorities from spring authentication
		List<String> authorityList = authentication.getAuthorities()
												   .stream()
												   .map(GrantedAuthority::getAuthority)
												   .collect(Collectors.toList());
		//authority list que devuelve?

		// Create JWS token
		String token = Jwts.builder()
						   .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
						   .setIssuer(jwtProperties.getIssuer())
						   .setSubject(authenticationRequest.getUsername())
						   .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationInMillis()))
						   .claim(jwtProperties.getAuthoritiesClaim(), authorityList)
						   .compact();

		return new AuthenticationResponse(token);
	}
//try catch en firma
}
