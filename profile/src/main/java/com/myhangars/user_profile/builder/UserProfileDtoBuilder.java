package com.myhangars.user_profile.builder;

import com.myhangars.model.UserProfile;
import com.myhangars.user_profile.dto.UserProfileDto;

public class UserProfileDtoBuilder {

    private UserProfileDto userProfileDto;

    public UserProfileDtoBuilder(UserProfile userProfile) {
        this.userProfileDto = new UserProfileDto();

        this.userProfileDto.setId(userProfile.getId());
        this.userProfileDto.setName(userProfile.getName());
        this.userProfileDto.setSurname(userProfile.getSurname());
        this.userProfileDto.setAddress(userProfile.getAddress());
        this.userProfileDto.setPhone(userProfile.getPhone());
        this.userProfileDto.setEmail(userProfile.getEmail());
    }

    public UserProfileDto getUserProfileDto() {
        return this.userProfileDto;
    }
}
