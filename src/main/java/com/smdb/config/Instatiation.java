package com.smdb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.smdb.domain.Post;
import com.smdb.domain.User;
import com.smdb.dto.AuthorDTO;
import com.smdb.dto.CommentsDTO;
import com.smdb.repository.PostRepository;
import com.smdb.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria", "maria@gmail");
		User alex = new User(null, "Alex", "alex@gmail");
		User bob = new User(null, "Bob", "bob@gmail");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viajar", "Vou viajar pra São Paulo, Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Bom dia, acordei feliz hoje.", new AuthorDTO(maria));
		
		CommentsDTO c1 = new CommentsDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentsDTO c2 = new CommentsDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentsDTO c3 = new CommentsDTO("Tenha um bom dia", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
