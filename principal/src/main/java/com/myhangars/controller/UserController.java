package com.myhangars.controller;

import com.myhangars.user.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @RequestMapping(value = "/user", params = { "username" }, method = RequestMethod.GET)
    public Boolean existsByUsername(@RequestParam("username") String username) {
        return this.userEntityRepository.existsByUsername(username);
    }
}
