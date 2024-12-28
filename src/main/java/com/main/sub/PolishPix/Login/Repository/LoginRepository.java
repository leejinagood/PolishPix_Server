package com.main.sub.PolishPix.Login.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import com.main.sub.PolishPix.Login.Entity.*;

public interface LoginRepository extends MongoRepository<Login, String> {
}