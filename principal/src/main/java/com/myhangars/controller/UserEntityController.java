package com.myhangars.controller;

import com.myhangars.model.UserEntity;
import com.myhangars.user.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserEntityController {

    @Autowired
    UserEntityService userEntityService;

    @RequestMapping(value = "/user-entity", method = RequestMethod.GET)
    public ResponseEntity<?> loadUserEntity(@RequestHeader("Authorization") String tokenExtended) {

        String token = tokenExtended.split(" ")[1];

        return new ResponseEntity<UserEntity>(
                this.userEntityService.getUserEntityId(token),
                HttpStatus.OK
        );
    }
}
