package com.hostmdy.ppm.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostmdy.ppm.domain.User;
import com.hostmdy.ppm.repository.UserRepository;
import com.hostmdy.ppm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return  (List<User>) userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

}
