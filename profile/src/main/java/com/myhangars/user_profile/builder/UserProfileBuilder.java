package com.myhangars.user_profile.builder;

import com.myhangars.model.UserProfile;
import com.myhangars.user_profile.dto.UserProfileDto;

public class UserProfileBuilder {

    private UserProfile userProfile;

    public UserProfileBuilder(UserProfileDto userProfileDto) {

        this.userProfile = new UserProfile();

        this.userProfile.setId(userProfileDto.getId());
        this.userProfile.setName(userProfileDto.getName());
        this.userProfile.setSurname(userProfileDto.getSurname());
        this.userProfile.setAddress(userProfileDto.getAddress());
        this.userProfile.setPhone(userProfileDto.getPhone());
        this.userProfile.setEmail(userProfileDto.getEmail());

    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

}
