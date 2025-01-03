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
	private String token;
	
	// 기본 생성자
    public Login() {}

    // 토큰 인증 후 claims에서 추출한 회원 정보를 위한 최소 생성자 생성 
    public Login(String email, String name, String profile) {
        this.email = email;
        this.name = name;
        this.profile = profile;
    }
}


