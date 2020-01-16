package com.smdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdb.domain.User;
import com.smdb.dto.UserDTO;
import com.smdb.repository.UserRepository;
import com.smdb.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		return user.get();
	}

	public User insert(User user) {
		user = userRepository.save(user);
		return user;
	}

	public User fromDto(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());

	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(String id, User user) {
		User userSalvo = findById(id);
		BeanUtils.copyProperties(user, userSalvo, "id");
		return userRepository.save(userSalvo);
		
	}

}
