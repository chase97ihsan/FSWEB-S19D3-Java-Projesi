package com.workintech.S7D3.controller;

import com.workintech.S7D3.dto.LoginRequest;
import com.workintech.S7D3.dto.LoginResponse;
import com.workintech.S7D3.dto.RegistrationMember;
import com.workintech.S7D3.entity.Member;
import com.workintech.S7D3.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public Member register(@RequestBody RegistrationMember registrationMember) {
        return authenticationService.register(registrationMember.getEmail(), registrationMember.getPassword());
    }
    @PostMapping("/login")
    public LoginResponse register(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest.getEmail(),loginRequest.getPassword());
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        authenticationService.delete();
    }

}
