
package com.RMS.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;

import com.RMS.model.UserDtls;
 

 
public interface UserRepository_INTERFACE extends JpaRepository<UserDtls, Integer> {
 
	public UserDtls findByEmail(UserDtls email);	

	public UserDtls findByEmail(String email);



	
 
}
