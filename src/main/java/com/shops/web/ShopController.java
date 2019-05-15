package com.shops.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shops.entities.Shop;
import com.shops.services.ShopServices;

//feature Display Shops 
//Functional Spec 

//(0) - list all shops
//(1) - Display list of nearby shops ==> Sorted By Distance (Liked shops and disliked one shouldn't be displayed
//(2) - Display list of preferred shops
//(3) - Like a shop and add to preferred list by user
//(4) - Remove shop ==> Remove it from preferred shop by user

@RestController
public class ShopController {
	@Autowired
	private ShopServices shopServices;
	
	/// spec (0)
	/// Get all shops for the non authenticated users
    @GetMapping("/api/shops")
    public List<Shop> allShops() {
        return shopServices.allShops();
    }
    
    /// spec (1)
    @GetMapping("/api/shops/nearest")
    public ResponseEntity<List<Shop>> nearestShops(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double distance) {
        // Get all nearest shops by distance related to the location of the user
    	Point userLocation = new Point(longitude,latitude);
        return ResponseEntity.ok(shopServices.nearestShops(userLocation,distance));
    }
    
    /// spec (2)
    @GetMapping("/api/shops/preferred")
    public List<Shop> preferredShops() {
        // Get user preferred shops
        return shopServices.allPreferredShops();
    }
    
    /// spec (3)
    @PostMapping("/api/shops/preferred")
    public List<Shop> addToPreferredShop(@RequestBody Shop shop) {
        return shopServices.addPreferredShop(shop.getId());
    }
    
    /// spec (4)
    @DeleteMapping("/api/shops/preferred")
    public List<Shop> removeFromPreferredShop(@RequestBody Shop shop) {
        return shopServices.removePreferredShop(shop.getId());
    }
}