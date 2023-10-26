package com.marcluiz.apirestmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcluiz.apirestmongo.domain.User;
import com.marcluiz.apirestmongo.repository.UserRepository;
import com.marcluiz.apirestmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	//auto dependency injection provided by Spring
	@Autowired
	private UserRepository repo;
	
	//returning all objects (type User) from the database
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
		}
}
