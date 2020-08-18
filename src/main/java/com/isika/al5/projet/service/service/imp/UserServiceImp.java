package com.isika.al5.projet.service.service.imp;


import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.isika.al5.projet.service.dto.UserDto;
import com.isika.al5.projet.service.entity.User;
import com.isika.al5.projet.service.service.UserService;
import com.isika.al5.projet.service.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	
	public UserServiceImp(UserRepository userRepository,ModelMapper modelMapper){
		this.userRepository=userRepository;
		this.modelMapper=modelMapper;	
	}

	@Override
	public UserDto save(UserDto userDto) {
    User user = modelMapper.map(userDto,User.class);
    user = userRepository.save(user);
    userDto.setId(user.getId());
	return userDto;
	}
	

	@Override
	public UserDto getById(Long id) {
		User u = userRepository.getOne(id);
		return  modelMapper.map(u, UserDto.class);
	}


	@Override
	public UserDto getByUsername(String username) {
		User u = userRepository.findByUsername(username);
		return modelMapper.map(u, UserDto.class);
	}

	 @Override
	    public List<UserDto> getAll() {
	        List<User> data = userRepository.findAll();
	        return  Arrays.asList(modelMapper.map(data, UserDto[].class));
	    }

	
	
}
