package com.myhangars.user.service;

import com.myhangars.model.UserEntity;

public interface UserEntityService {
    public UserEntity findById(long id);
    public UserEntity getByUsername(String username);
    public UserEntity getUserEntityId(String token);
}
