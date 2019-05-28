/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.services.impl;

import com.cristianroot.springrestsecurityexample.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//indiar de que repositorio quiero sacar lo datos del ususaario implementa userdetails.
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final ClientRepository clientRepository;

	public CustomUserDetailsServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return clientRepository.findByNameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
	}

}
