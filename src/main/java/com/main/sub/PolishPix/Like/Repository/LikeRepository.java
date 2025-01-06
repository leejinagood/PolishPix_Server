package com.main.sub.PolishPix.Like.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

import com.main.sub.PolishPix.Like.Entity.*;

public interface LikeRepository extends MongoRepository<Like, String> {
}