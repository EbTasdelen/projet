package com.isika.al5.projet.service.controller;






import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isika.al5.projet.service.dto.UserDto;
import com.isika.al5.projet.service.service.imp.UserServiceImp;
import com.isika.al5.projet.service.util.ApiPath;
import com.isika.al5.projet.service.util.Pages;

@CrossOrigin
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
    
    @PostMapping
    public ResponseEntity<UserDto> createProject(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(userServiceImp.save(user));
    }
    
   
   @Secured(value="ROLE_ADMIN")
   @GetMapping()
   public ResponseEntity<List<UserDto>> getAll() {
       List<UserDto> users = userServiceImp.getAll();
       return ResponseEntity.ok(users);
   }
   @PutMapping("/{id}")
   public ResponseEntity<UserDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody UserDto userDto) {
       return ResponseEntity.ok(userServiceImp.update(id, userDto));
   }
   
  
   @DeleteMapping("/{id}")
   public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
       return ResponseEntity.ok(userServiceImp.delete(id));
   }
   
   @GetMapping("/pagination")
   public ResponseEntity<Pages<UserDto>> getPagination(Pageable pageable) {
   Pages<UserDto> page = userServiceImp.getAllPageable(pageable);
   return  ResponseEntity.ok(page);
      
   }
   
   
   
   }

	   
   
   
