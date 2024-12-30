package com.main.sub.PolishPix.Login.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

import com.main.sub.PolishPix.Login.Entity.*;

public interface LoginRepository extends MongoRepository<Login, String> {
	Optional<Login> findByEmail(String email);
}