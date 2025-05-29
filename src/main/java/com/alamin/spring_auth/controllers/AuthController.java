package com.alamin.spring_auth.controllers;

import com.alamin.spring_auth.config.auth.TokenProvider;
import com.alamin.spring_auth.dots.JwtDto;
import com.alamin.spring_auth.dots.SignInDto;
import com.alamin.spring_auth.dots.SignUpDto;
import com.alamin.spring_auth.entities.User;
import com.alamin.spring_auth.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenProvider tokenService;
    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<?>signUp(@RequestBody @Valid SignUpDto data){

        service.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtDto>signIn(@RequestBody @Valid SignInDto data){
        var usernamePassword=new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var authUser=authenticationManager.authenticate(usernamePassword);
        var accessToken=tokenService.generateAccessToken((User)authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }
}
