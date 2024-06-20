package com.api.amarelo.controller;

import com.api.amarelo.dto.LoginDTO;
import com.api.amarelo.dto.RegisterDTO;
import com.api.amarelo.dto.TokenDTO;
import com.api.amarelo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }

}
