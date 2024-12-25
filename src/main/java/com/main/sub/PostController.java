package com.main.sub;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.main.sub.Entity.Post;
import com.main.sub.Repository.PostRepository;
import com.main.sub.Dto.PostDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 생성자를 생성해주는..
@RestController // 데이터 리턴 서버
@RequestMapping("/Contetnt")
public class PostController {
	
	// DB
	private final PostRepository postRepository;
	
	
	@GetMapping("/view")
	public List<Post> findAll() { // 전체 출력 
		return postRepository.findAll();
	}
	
		
}