package com.main.sub.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.main.sub.Entity.*;

public interface UserRepository extends MongoRepository<User, String>{

}