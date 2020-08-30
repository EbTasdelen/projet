package com.isika.al5.projet.service.controller;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isika.al5.projet.service.dto.UserDto;
import com.isika.al5.projet.service.service.imp.UserServiceImp;
import com.isika.al5.projet.service.util.ApiPath;


@RestController
@RequestMapping(ApiPath.UserCtrl.CTRL)
public class UserController {

    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }
    
  

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id", required = true) Long id) {
       UserDto user = userServiceImp.getById(id);
        return ResponseEntity.ok(user);
    }

    
   /* @GetMapping("/users")
    public Map<String, Object> getUsers(HttpSession httpSession){
	   SecurityContext securityContext= (SecurityContext)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
	   String username=securityContext.getAuthentication().getName();
	   List<String> roles= new ArrayList<>();
	   for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {// return une collection 
	   roles.add(ga.getAuthority());
   }
       Map<String,Object> params=new HashMap<>();
	   params.put("username",username);
       params.put("roles",roles);
       return params;
}*/
}
	   
   
   
