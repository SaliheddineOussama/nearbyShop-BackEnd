package com.shops.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserAcc {
	@Id
	private String id;
	@Indexed(unique=true)
	private String email; // username as email
	private String password;
	private boolean active;
	@DBRef 
	private List<Shop> preferredShops = new ArrayList<>();
}
