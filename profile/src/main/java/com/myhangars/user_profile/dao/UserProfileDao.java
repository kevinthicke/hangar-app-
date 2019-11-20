package com.myhangars.user_profile.dao;

import com.myhangars.model.UserProfile;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface UserProfileDao {

    public List<UserProfile> findAll();
    public Optional<UserProfile> findById(long id);
    public UserProfile save(UserProfile userProfile);
}
