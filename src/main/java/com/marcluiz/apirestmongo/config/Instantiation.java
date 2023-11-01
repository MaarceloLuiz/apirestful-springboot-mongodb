package com.marcluiz.apirestmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.marcluiz.apirestmongo.domain.Post;
import com.marcluiz.apirestmongo.domain.User;
import com.marcluiz.apirestmongo.dto.AuthorDTO;
import com.marcluiz.apirestmongo.dto.CommentDTO;
import com.marcluiz.apirestmongo.repository.PostRepository;
import com.marcluiz.apirestmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		//instantiating the user before creating the posts
		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Grey", "bob@gmail.com");
		
		//saving the Users to the database
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		//instead of inserting a User directly into the argument, you should now instantiate an AuthorDTO using the User object as a reference
		Post post1 = new Post(null, sdf.parse("21/03/2023"), "Finally Holidays!", "I'm travelling to Ireland!", new AuthorDTO(u1));
		Post post2 = new Post(null, sdf.parse("23/03/2023"), "GMorning!", "Top of the morning to ya", new AuthorDTO(u1));
		
		//instantiating the comments
		CommentDTO c1 = new CommentDTO("Have a nice trip!", sdf.parse("21/03/2023"), new AuthorDTO(u2));
		CommentDTO c2 = new CommentDTO("Enjoy!", sdf.parse("22/03/2023"), new AuthorDTO(u3));
		CommentDTO c3 = new CommentDTO("Have a great day!", sdf.parse("23/03/2023"), new AuthorDTO(u2));
		
		//adding comments to their respective posts
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		//saving the Posts to the database
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(u1); //now the Post references is also going to be stored along the User
	}

}
