package com.shops.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shops.dao.UserRepository;
import com.shops.entities.UserAcc;

@Service
@Transactional
public class UserServicesImpl implements UserServices{

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public UserAcc getAuthenticatedUser() {
		// get some mail for the authenticated user
		// find the user by email and return the result ==> Security module (TODO After)
        String email = "rachid@gmail.com";
        UserAcc user = findByEmail(email);
        return user;
	}

	@Override
	public void updateUser(UserAcc user) {
        userRepository.findByEmail(user.getEmail());
        userRepository.save(user);
	}

	@Override
	public UserAcc findByEmail(String email) {
	
		return userRepository.findByEmail(email);
	}

}
