package com.main.sub.PolishPix.Login.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.main.sub.PolishPix.Login.Entity.Login;
import com.main.sub.PolishPix.Login.Repository.LoginRepository;
import com.main.sub.PolishPix.Login.Dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 생성자를 생성해주는..
@RestController // 데이터 리턴 서버
@RequestMapping("/login")
public class LoginController {
	
	// DB
	private final LoginRepository loginRepository;
	
	@PostMapping("/insert")
	public Login save(@RequestBody LoginDto dto) { // {"email":"xxx","password":"xxx"}
		Login loginEntity = loginRepository.save(dto.toEntity());
		
		return loginEntity; // MessageConverter 발동 -> 자바오브제그를 Json 변환해서 응답함.
	}

	
}