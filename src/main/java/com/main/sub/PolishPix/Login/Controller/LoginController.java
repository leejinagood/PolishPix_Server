package com.main.sub.PolishPix.Login.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.sub.PolishPix.Login.Entity.Login;
import com.main.sub.PolishPix.Login.Repository.LoginRepository;
import com.main.sub.PolishPix.Login.Dto.LoginDto;
import com.main.sub.PolishPix.Login.Service.LoginService;
import com.main.sub.PolishPix.User.Entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 생성자를 생성해주는..
@RestController // 데이터 리턴 서버
public class LoginController {
	
	// DB
	private final LoginRepository loginRepository;
	
	//서비스 로직 
	private final LoginService loginService;

	@PostMapping("/Signup")
    public ResponseEntity<?> signup(@RequestBody LoginDto dto) {
        try {
        	Login loginEntity = loginService.signup(dto);
            return ResponseEntity.ok(loginEntity);
        } catch (IllegalArgumentException e) {
            // 이메일 중복 예외 처리
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto dto) {
	    try {
	        String loginMessage = loginService.login(dto.getEmail(), dto.getPassword());
	        return ResponseEntity.ok(loginMessage);
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	        return ResponseEntity.status(500).body("Error: " + e.getMessage());
	    }
	}
	


}
