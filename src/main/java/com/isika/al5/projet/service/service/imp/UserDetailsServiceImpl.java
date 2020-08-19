package com.isika.al5.projet.service.service.imp;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.isika.al5.projet.service.entity.User;
import com.isika.al5.projet.service.repository.UserRepository;





@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepository;
	

	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username);
	        if(user == null){
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
	    }
}
	 


