package com.myhangars.user.builder;

import com.myhangars.model.UserEntity;
import com.myhangars.user.dto.UserDto;

public class UserDtoBuilder {

    private UserDto userDto;

    public UserDtoBuilder(UserEntity userEntity) {
        this.userDto = new UserDto();
        this.userDto.setUsername(userEntity.getUsername());
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
