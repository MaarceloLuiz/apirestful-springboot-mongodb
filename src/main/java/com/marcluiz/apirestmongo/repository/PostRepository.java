package com.marcluiz.apirestmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcluiz.apirestmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//https://www.mongodb.com/docs/manual/reference/operator/query/regex/
	
	//?0 - means that its going to be used or first parameter, in that case the String text
	//i - to ignore upper and lower case
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
