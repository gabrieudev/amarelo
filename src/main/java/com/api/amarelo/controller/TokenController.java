package com.api.amarelo.controller;

import com.api.amarelo.dto.UserLoginRequest;
import com.api.amarelo.dto.UserLoginResponse;
import com.api.amarelo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class TokenController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(userLoginRequest));
    }

}
