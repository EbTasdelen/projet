package com.isika.al5.projet.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isika.al5.projet.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	 User findByUsername(String username); 

}
