package com.isika.al5.projet.service.controller;



import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<UserDto> createProject(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userServiceImp.save(userDto));
    }

}