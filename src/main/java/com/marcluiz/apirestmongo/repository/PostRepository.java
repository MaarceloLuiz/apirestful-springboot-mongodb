package com.marcluiz.apirestmongo.repository;

import java.util.Date;
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
	
	//check on the website the Comparison and Logical Query to build this statement
	//bigger than minDate AND lower than maxDate, check on the title OR in the body OR in the comments (in 'comments' we have to search the text using the parameter in the CommentDTO class)
	@Query("{ $and: [ {date: { $gte: ?1 } }, {date: { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
