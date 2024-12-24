package com.main.sub.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {
	
	@Id
	private String _id;
	private String email;
	private String password;
	private String phone;
	private String name;
	private String profile;
}