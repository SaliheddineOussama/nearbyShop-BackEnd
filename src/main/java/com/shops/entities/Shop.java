package com.shops.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("shops")
@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class Shop {
	@Id
	private String id;
	private String picture;
	private String name;
	private String email;
	private String city;
	private GeoJsonPoint location;
}
