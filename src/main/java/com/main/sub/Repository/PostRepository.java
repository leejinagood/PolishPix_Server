package com.main.sub.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.main.sub.Entity.*;

public interface PostRepository extends MongoRepository<Post, String>{

}