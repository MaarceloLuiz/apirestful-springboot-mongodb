package com.marcluiz.apirestmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcluiz.apirestmongo.domain.User;
import com.marcluiz.apirestmongo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
	
	//auto dependency injection provided by Spring
	@Autowired
	private UserRepository repo;
	
	//returning all objects (type User) from the database
	public List<User> findAll(){
		return repo.findAll();
	}
}
