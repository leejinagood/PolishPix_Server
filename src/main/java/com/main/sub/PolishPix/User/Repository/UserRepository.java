package com.main.sub.PolishPix.User.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.main.sub.PolishPix.User.Entity.User;

public interface UserRepository extends MongoRepository<User, String>{
}