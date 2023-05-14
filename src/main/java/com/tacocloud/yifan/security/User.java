package com.tacocloud.yifan.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tacocloud.yifan.tacos.TacoOrder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class User implements Serializable, UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private final String username;
	
	private final String password;
	
	private final String fullname;
	
	private final String street;
	
	private final String city;
	
	private final String state;
	
	private final String zip;
	
	private final String phoneNumber;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TacoOrder> tacoOrders = new ArrayList<>();

	@Override
	public boolean isAccountNonExpired() {
		return true;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 表示所有用戶都被授予ROLE_USER權限
		// 沒有必要禁用用戶，所以所有以is開頭的方法均回傳true
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
