package com.isika.al5.projet.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isika.al5.projet.service.entity.Role;







public interface RolesRepository extends JpaRepository<Role,Long> {
	
	 Optional<Role> findById(Long id); 

}
