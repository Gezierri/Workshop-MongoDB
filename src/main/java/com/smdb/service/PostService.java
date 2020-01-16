package com.smdb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdb.domain.Post;
import com.smdb.repository.PostRepository;
import com.smdb.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> postSalvo = postRepository.findById(id);
		if (!postSalvo.isPresent()) {
			throw new ObjectNotFoundException("Post não encontrado");
		}
		return postSalvo.get();
	}
	
	public List<Post> findByTitle(String text){
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		 maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		 return postRepository.fullSearch(text, minDate, maxDate);
	}
}
