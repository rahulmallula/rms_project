package com.RMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.transaction.annotation.Transactional;

import com.RMS.model.UserDtls;
import com.RMS.repository.UserRepository_INTERFACE;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository_INTERFACE userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	@Transactional
	public UserDtls createUser(UserDtls user) {
        System.out.print(user);
		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");

		return userRepo.save(user);
	}

	


@Override
public void removeSessionMessage() {

	HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
			.getSession();

	session.removeAttribute("msg");
}

}
