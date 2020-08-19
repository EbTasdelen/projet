package com.isika.al5.projet.service.service.imp;


import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.isika.al5.projet.service.dto.UserDto;
import com.isika.al5.projet.service.dto.Registration;
import com.isika.al5.projet.service.entity.User;
import com.isika.al5.projet.service.service.UserService;
import com.isika.al5.projet.service.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	
	public UserServiceImp(UserRepository userRepository,ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder){
		this.userRepository=userRepository;
		this.modelMapper=modelMapper;	
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
	}

	@Override
	public UserDto save(UserDto userDto) {
    User user= modelMapper.map(userDto,User.class);
    user = userRepository.save(user);
    userDto.setId(user.getId());
	return userDto;
	}
	

	@Override
	public UserDto getById(Long id) {
		User user = userRepository.getOne(id);
		return  modelMapper.map(user, UserDto.class);
	}


	@Override
	public UserDto getByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return modelMapper.map(user, UserDto.class);
	}

	 @Override
	    public List<UserDto> getAll() {
	        List<User> data = userRepository.findAll();
	        return  Arrays.asList(modelMapper.map(data, UserDto[].class));
	    }
	 
	 
	 @Transactional
	public Boolean register(Registration registration) {
		try {
		User user= new User();
		user.setName(registration.getName());
		user.setSurname(registration.getSurname());
		user.setUsername(registration.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
		user.setEmail(registration.getEmail());
		userRepository.save(user);
		return Boolean.TRUE;
		}catch (Exception e) {
			log.error("Echec de l'enregistrement,e");
			return Boolean.FALSE; 
		   }
	 }
}
