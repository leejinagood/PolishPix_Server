package com.main.sub.PolishPix.Login.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "user")
public class Login {
	
	@Id
	private String _id;
	private String email;
	private String password;
	private String phone; 
	private String name;
	private String profile;
}