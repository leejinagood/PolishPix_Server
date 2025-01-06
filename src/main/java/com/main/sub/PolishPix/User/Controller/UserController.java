package com.main.sub.PolishPix.User.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.sub.PolishPix.Login.Dto.LoginDto;
import com.main.sub.PolishPix.Login.Service.LoginService;
import com.main.sub.PolishPix.Post.Entity.Post;
import com.main.sub.PolishPix.Post.Repository.PostRepository;
import com.main.sub.PolishPix.Post.Service.PostService;
import com.main.sub.PolishPix.User.Dto.UserDto;
import com.main.sub.PolishPix.User.Entity.User;
import com.main.sub.PolishPix.User.Repository.UserRepository;
import com.main.sub.PolishPix.User.Service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI
@RestController // 데이터 리턴 서버
@RequestMapping("/User")
public class UserController {
	
	// DI
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	
	//서비스 로직 
	private final UserService userService;
	private final PostService postService;
	
	@GetMapping("/view")
	public List<User> findAll() { // 리턴을 JavaObject로 하면 스프링 내부적으로 JSON으로 자동 변환 해준다.
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User findById(@PathVariable("id") String id) { //아이디별로 사용 조회하기
	    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
	}
	
	//프로필 수정 
	@PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto dto){
		try {
			User ExistUserId = this.userService.getId(dto.getId());
	        if (ExistUserId == null) {
	            return ResponseEntity.status(404).body("아이디 존재하지 않");
	        }
	        
	        User updatedUser = this.userService.update(dto);
	        Post updatedPostUser = this.postService.update(dto);

	        return ResponseEntity.ok(updatedUser);
		}catch (Exception e) {
	        return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
	    }
    }
	
}