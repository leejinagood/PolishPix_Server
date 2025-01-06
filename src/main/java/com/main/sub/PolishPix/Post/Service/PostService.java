package com.main.sub.PolishPix.Post.Service;

import org.springframework.stereotype.Service;

import com.main.sub.PolishPix.Post.Dto.PostDto;
import com.main.sub.PolishPix.Post.Entity.Post;
import com.main.sub.PolishPix.Post.Repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    

    public Post update(PostDto postDto) {
    	Post existingUser
        // 기존 사용자 정보를 업데이트
        existingUser.setEmail(userDto.getEmail());
        existingUser.setName(userDto.getName());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setProfile(userDto.getProfile());

        // 변경된 사용자 정보를 저장
        return this.postRepository.save(existingUser);
    }
    
}