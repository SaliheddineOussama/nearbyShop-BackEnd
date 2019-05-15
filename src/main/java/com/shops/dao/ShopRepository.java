package com.shops.dao;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.shops.entities.Shop;

public interface ShopRepository extends MongoRepository<Shop, String>{

	// find shops by location based on a radius
	public List<Shop> findByLocationNear(Point locationUser, Distance distance);
}
