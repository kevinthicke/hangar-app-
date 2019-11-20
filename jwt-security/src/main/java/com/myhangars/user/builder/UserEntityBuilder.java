package com.myhangars.user.builder;

import com.myhangars.model.UserEntity;
import com.myhangars.user.dto.UserDto;
public class UserEntityBuilder {

    private UserEntity userEntity;

    public UserEntityBuilder(UserDto userDto) {
        this.userEntity = new UserEntity();

        this.userEntity.setUsername(userDto.getUsername());
        this.userEntity.setPassword(userDto.getPassword());
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
