package com.marcluiz.apirestmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marcluiz.apirestmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
}