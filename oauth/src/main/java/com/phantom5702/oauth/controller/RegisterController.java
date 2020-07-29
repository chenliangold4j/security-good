package com.phantom5702.oauth.controller;

import com.phantom5702.oauth.dao.UserDao;
import com.phantom5702.oauth.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @PostMapping("/register")
    public Object register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phone") String phone) {
        String encode = passwordEncoder.encode(password);
        return userDao.insertUser(username, encode, phone);
    }
}
