package com.isika.al5.projet.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.isika.al5.projet.service.dto.Registration;
import com.isika.al5.projet.service.dto.Login;
import com.isika.al5.projet.service.dto.TokenResponse;
import com.isika.al5.projet.service.entity.User;
import com.isika.al5.projet.service.repository.UserRepository;
import com.isika.al5.projet.service.security.JwtTokenUtil;
import com.isika.al5.projet.service.service.imp.UserServiceImp;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/token")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody Login login) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        final User user = userRepository.findByUsername(login.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody Registration registration) throws AuthenticationException {
        Boolean response = userServiceImp.register(registration);
        return ResponseEntity.ok(response);
    }

}
