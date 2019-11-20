package com.myhangars.user_profile.dao;

import com.myhangars.model.UserProfile;
import com.myhangars.user_profile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserProfileDaoImpl implements UserProfileDao {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public List<UserProfile> findAll() {
        return this.userProfileRepository.findAll();
    }

    @Override
    public Optional<UserProfile> findById(long id) {
        //return this.userProfileRepository.findById(id).;
        return this.userProfileRepository.findById(id);
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        return this.userProfileRepository.save(userProfile);
    }

}
