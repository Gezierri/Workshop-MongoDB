package com.smdb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smdb.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

	
}
