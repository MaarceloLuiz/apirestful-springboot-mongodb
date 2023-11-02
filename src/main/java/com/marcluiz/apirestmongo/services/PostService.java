package com.marcluiz.apirestmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcluiz.apirestmongo.domain.Post;
import com.marcluiz.apirestmongo.repository.PostRepository;
import com.marcluiz.apirestmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	//auto dependency injection provided by Spring
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	//using Spring Query methods
	/*
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	*/
	
	//using our implemented search method
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
}
