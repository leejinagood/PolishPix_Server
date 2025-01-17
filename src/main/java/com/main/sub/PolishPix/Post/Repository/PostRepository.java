package com.main.sub.PolishPix.Post.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import com.main.sub.PolishPix.Post.Entity.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    // User 객체 내의 user_id를 기준으로 게시글을 찾는 메서드
	@Query("{'user.user_id': ?0}")
    List<Post> findByUserId(String user_id);
}