package com.shops.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shops.dao.ShopRepository;
import com.shops.dao.UserRepository;
import com.shops.entities.Shop;
import com.shops.entities.UserAcc;

// feature Display Shops 
//Functional Spec 

//(0) - list all shops
//(1) - Display list of nearby shops ==> Sorted By Distance (Liked shops and disliked one shouldn't be displayed
//(2) - Display list of preferred shops
//(3) - Like a shop and add to preferred list by user
//(4) - Remove shop ==> Remove it from preferred shop by user

@Service
@Transactional
public class ShopServicesImpl implements ShopServices{
	@Autowired
	private ShopRepository shopRepository;
	@Autowired UserServices userServices;
	
	@Override
	public List<Shop> allShops() {
		return shopRepository.findAll(); // Display all shops ==> Spec (0) **
	}

	@Override
	public List<Shop> nearestShops(Point locationUser, double distance) {
		// get the preferred shops related to the authenticated user
		List<Shop> preferredShops = allPreferredShops();
		// get the nearest shop by a distance related to the position of the user
		List<Shop> nearestShop = shopRepository.findByLocationNear(locationUser, new Distance(distance, Metrics.KILOMETERS));
		// separate the nearest shop with the preferred one ==> Spec (1) **
		nearestShop.removeAll(preferredShops);
		
		return nearestShop;
	}
	
	@Override
	public List<Shop> allPreferredShops() {
		// Spec (2)
		return userServices.getAuthenticatedUser().getPreferredShops(); // return all preferred shops related to the connected user
	}
	
	@Override
	public List<Shop> addPreferredShop(String id) {
		// get the authenticated user information
        UserAcc user = userServices.getAuthenticatedUser();
        // find the shop that we would like to add it to the preferred list shops related to the user
        Shop shopLiked = shopRepository.findById(id).get();
        // verify if the shop is already exist in the preferred list
        Shop shopExistInPreferred = user.getPreferredShops().stream().
        		filter(shop -> id.equals(shop.getId())).findAny().orElse(null);
        
        if (shopLiked!=null && shopExistInPreferred == null) {
            List<Shop> likedShops = user.getPreferredShops(); // get liked Shops from the authenticated user infos
            likedShops.add(shopLiked); // add the one to the list Spec(3)
            userServices.updateUser(user); // save changes
        }
        return (List<Shop>) user.getPreferredShops(); // re-initialize the preferred shops related to the user from the server
	}

	@Override
	public List<Shop> removePreferredShop(String id) {

        UserAcc user = userServices.getAuthenticatedUser();
        // remove the preferred shop (related to the authenticated user) from the preferredShop list based on ID
        user.getPreferredShops().removeIf(shop -> id.equals(shop.getId()));
        // Save the changes
        userServices.updateUser(user);
        // Display an update of the preferred Shops from the server (
        return (List<Shop>) user.getPreferredShops(); // ==> Spec(4)
	}
}
