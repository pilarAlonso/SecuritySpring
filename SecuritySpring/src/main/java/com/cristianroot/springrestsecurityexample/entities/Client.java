/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.entities;

import com.cristianroot.springrestsecurityexample.constants.AuthorityName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Client implements UserDetails {//usr details interfaz de spring seurity,minimo role user y role admin

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String name;

	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)

	private List<Purchase> purchaseList;

	@NotNull
	private String password;

	@ManyToMany
	private List<Authority> authorities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return  name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}
//mapear autrithy a granted autorithy simple granted auhority es sun hijo de authority
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities.stream()
						  .map(Authority::getName)
						  .map(AuthorityName::name)
						  .map(SimpleGrantedAuthority::new)
						  .collect(Collectors.toList());
	}
//metodos paras aber si el usuario esta activo se sobreescriben con la interfaz
	//return true ,luego se validara , enable puedo poner false para test
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
