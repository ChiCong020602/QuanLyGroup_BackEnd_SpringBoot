package com.vti.testing.controller;

import com.vti.testing.dto.LoginDTO;
import com.vti.testing.entity.User;
import com.vti.testing.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/login")
    public ResponseEntity login(Principal principal){
        System.out.println(principal);
        System.out.println("----------");
        User user = userService.getUserByUserName(principal.getName());
        if(user.getStatus()==0){
            return ResponseEntity.ok("not ok");
        }
        return ResponseEntity.ok(modelMapper.map(user, LoginDTO.class));
    }
}
