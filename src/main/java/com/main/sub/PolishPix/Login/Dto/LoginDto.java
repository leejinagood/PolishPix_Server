package com.main.sub.PolishPix.Login.Dto;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
import com.main.sub.PolishPix.Login.Entity.Login;
import lombok.Data;

@Data
public class LoginDto {
	private String email;
	private String password;
	private String phone;
    private String name;
    private String profile;
	
	public Login toEntity() {
		//user 설정 
		Login login = new Login();
		login.setEmail(email);
		login.setPassword(password);
		login.setPhone(phone);
		login.setName(name);
		login.setProfile(profile);
		
        
		return login;
	}
}