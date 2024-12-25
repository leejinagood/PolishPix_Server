package com.main.sub;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.main.sub.Entity.User;
import com.main.sub.Repository.UserRepository;
import com.main.sub.Dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI
@RestController // 데이터 리턴 서버
@RequestMapping("/user")
public class UserController {
	
	// DI
	private final UserRepository userRepository;
	
	
	@GetMapping("/view")
	public List<User> findAll() { // 리턴을 JavaObject로 하면 스프링 내부적으로 JSON으로 자동 변환 해준다.
		return userRepository.findAll();
	}
	
		
}