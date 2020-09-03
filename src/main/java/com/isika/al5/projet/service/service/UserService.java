package com.isika.al5.projet.service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.isika.al5.projet.service.dto.UserDto;
import com.isika.al5.projet.service.util.Pages;


public interface UserService {

	UserDto getById (Long id);  
	UserDto getByUsername(String username);
	UserDto save(UserDto person);
	List<UserDto> getAll();
	Boolean delete(Long id);
	UserDto update(Long id, UserDto userDto);
	Pages<UserDto> getAllPageable(Pageable pageable);

}
