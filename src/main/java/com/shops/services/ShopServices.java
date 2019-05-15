package com.shops.services;

import java.util.List;

import org.springframework.data.geo.Point;

import com.shops.entities.Shop;

public interface ShopServices {

	public List<Shop> allShops();
	public List<Shop> nearestShops(Point locationUser, double distance);
	public List<Shop> addPreferredShop(String id);
	public List<Shop> removePreferredShop(String id);
	public List<Shop> allPreferredShops();
}
