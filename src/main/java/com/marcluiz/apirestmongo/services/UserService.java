package com.marcluiz.apirestmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcluiz.apirestmongo.domain.User;
import com.marcluiz.apirestmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
