package com.main.sub.PolishPix.User.Dto;

import com.main.sub.PolishPix.User.Entity.User;

import lombok.Data;

@Data
public class UserDto {
	private String email;
	private String password;
	private String phone;
	private String name;
	private String profile;
	
	
	public User toEntity() {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setName(name);
		user.setProfile(profile);
		return user;
	}
}