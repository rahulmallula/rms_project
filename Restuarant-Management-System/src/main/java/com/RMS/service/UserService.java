package com.RMS.service;



import com.RMS.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);

//	public boolean checkEmail(String email);
	
	public void removeSessionMessage();


}