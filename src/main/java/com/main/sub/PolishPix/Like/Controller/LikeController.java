package com.main.sub.PolishPix.Like.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.sub.PolishPix.Like.Entity.Like;
import com.main.sub.PolishPix.Like.Repository.LikeRepository;
import com.main.sub.PolishPix.Like.Dto.LikeDto;
import com.main.sub.PolishPix.Like.Service.LikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 생성자를 생성해주는..
@RestController // 데이터 리턴 서버
public class LikeController {
	
	// DB
	private final LikeRepository likeRepository;
	
	//서비스 로직 
	private final LikeService likeService;



}
