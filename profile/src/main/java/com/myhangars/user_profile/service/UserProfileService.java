package com.myhangars.user_profile.service;

import com.myhangars.model.UserProfile;
import com.myhangars.user_profile.dto.UserProfileDto;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserProfileService {

    public UserProfile getById(long id);
    public UserProfile insert(UserProfile userProfile);
    public UserProfile update(long id, UserProfile userProfile);
    public UserProfile getByUserEntityId(long id);
}
