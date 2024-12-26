package com.main.sub.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.main.sub.Entity.*;

public interface PostRepository extends MongoRepository<Post, String> {
    // User 객체 내의 user_id를 기준으로 게시글을 찾는 메서드
    List<Post> findByUseruser_id(String user_id);
}