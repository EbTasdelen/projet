package com.isika.al5.projet.service.service;

import java.util.List;
import com.isika.al5.projet.service.dto.UserDto;


public interface UserService {

	UserDto getById (Long id);  
	UserDto getByUsername(String username);
	UserDto save(UserDto person);
	List<UserDto> getAll();
	Boolean delete(Long id);
	UserDto update(Long id, UserDto userDto);

}
