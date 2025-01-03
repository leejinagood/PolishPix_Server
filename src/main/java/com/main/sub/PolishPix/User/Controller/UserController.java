package com.main.sub.PolishPix.User.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.main.sub.PolishPix.Post.Entity.Post;
import com.main.sub.PolishPix.User.Dto.UserDto;
import com.main.sub.PolishPix.User.Entity.User;
import com.main.sub.PolishPix.User.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI
@RestController // 데이터 리턴 서버
@RequestMapping("/User")
public class UserController {
	
	// DI
	private final UserRepository userRepository;
	
	
	@GetMapping("/view")
	public List<User> findAll() { // 리턴을 JavaObject로 하면 스프링 내부적으로 JSON으로 자동 변환 해준다.
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User findById(@PathVariable("id") String id) { //아이디별로 사용 조회하기
	    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
	}
		
}