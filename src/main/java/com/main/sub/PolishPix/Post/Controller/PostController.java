package com.main.sub.PolishPix.Post.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.main.sub.PolishPix.Post.Dto.PostDto;
import com.main.sub.PolishPix.Post.Entity.Post;
import com.main.sub.PolishPix.Post.Repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 생성자를 생성해주는..
@RestController // 데이터 리턴 서버
@RequestMapping("/Content")
public class PostController {
	
	// DB
	private final PostRepository postRepository;
	
	
	@GetMapping("/view")
	public List<Post> findAll() { // 전체 출력 
		return postRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Post findById(@PathVariable("id") String id) { //아이디별로 게시글 조회하기
	    return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
	}
	
	@GetMapping("/user/{user_id}")
    public List<Post> findByUserId(@PathVariable("user_id") String userId) {
        return postRepository.findByUserId(userId);  // Repository 메서드 호출
    }

	
}