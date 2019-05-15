package com.shops.services;

import com.shops.entities.UserAcc;

public interface UserServices {
	
	public UserAcc getAuthenticatedUser();
	public void updateUser(UserAcc user);
	public UserAcc findByEmail(String email);
}
