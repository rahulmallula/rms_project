package com.RMS.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.RMS.model.UserDtls;

import com.RMS.repository.UserRepository_INTERFACE;

@Component
public  class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository_INTERFACE userRepo;
	
    
	public UserDetails loadUserByUsername(UserDtls username) throws UsernameNotFoundException {

		UserDtls user = userRepo.findByEmail(username);
		System.out.println(user);
		if (user != null) {
			return new CustomUserDetails(user);
		}

		throw new UsernameNotFoundException("user not available");
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}