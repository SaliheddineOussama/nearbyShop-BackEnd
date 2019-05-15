package com.shops.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shops.entities.Shop;

public interface ShopRepository extends MongoRepository<Shop, String>{

}
