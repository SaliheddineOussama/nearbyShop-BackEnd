package com.shops;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.shops.dao.ShopRepository;
import com.shops.dao.UserRepository;
import com.shops.entities.*;

@SpringBootApplication
public class NearbyShopsBackEndPartApplication {

	public static void main(String[] args) {
		SpringApplication.run(NearbyShopsBackEndPartApplication.class, args);
	}

	 @Bean
	    CommandLineRunner start(ShopRepository shopRepository, UserRepository  userRepository){
	        return args->{      
	        	/*shopRepository.deleteAll();
	            shopRepository.save(new Shop(null,"http://placehold.it/150x150", "Gushkool", "leilaware@gushkool.com", "Rabat", new GeoJsonPoint(-6.81134, 33.95564)));
	            userRepository.save(new UserAcc(null, "rachid@gmail.com","456",true,null));
	            userRepository.findAll().forEach(System.out::println);
	            */
	        	shopRepository.findAll().forEach(System.out::println);
	        };
	 }
}
