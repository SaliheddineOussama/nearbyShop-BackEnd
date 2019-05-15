package com.shops.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shops.entities.UserAcc;

public interface UserRepository extends MongoRepository<UserAcc, String>{

	public UserAcc findByEmail(String userName);
}
