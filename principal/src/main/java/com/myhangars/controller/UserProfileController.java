package com.myhangars.controller;

import com.myhangars.model.UserProfile;
import com.myhangars.user_profile.builder.UserProfileBuilder;
import com.myhangars.user_profile.builder.UserProfileDtoBuilder;
import com.myhangars.user_profile.dto.UserProfileDto;
import com.myhangars.user_profile.repository.UserProfileRepository;
import com.myhangars.user_profile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = "/user-profile", params = { "user_id" }, method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserProfile(@RequestParam(value = "user_id") @Min(0) long userId,
                                               @RequestBody @Valid UserProfileDto userProfileDto) {

        UserProfile userProfile = new UserProfileBuilder(userProfileDto).getUserProfile();
        UserProfile updatedUserProfile = this.userProfileService.update(userId, userProfile);

        return new ResponseEntity<Object>(
                new UserProfileDtoBuilder(updatedUserProfile)
                        .getUserProfileDto(),
                HttpStatus.OK
        );
    }

}
