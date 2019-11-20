package com.myhangars.user_profile.service;

import com.myhangars.exception.GenericException;
import com.myhangars.model.UserEntity;
import com.myhangars.model.UserProfile;
import com.myhangars.user_profile.dao.UserProfileDao;
import com.myhangars.user_profile.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements  UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;


    @Override
    public UserProfile getById(long id) {

        return this.userProfileDao
                .findById(id)
                .orElseThrow(() -> new GenericException.NotFound(id));
    }

    @Override
    public UserProfile getByUserEntityId(long id) {
        return this.userProfileDao.findAll()
                .stream()
                .filter(userProfile -> userProfile.getUserEntity().getId() == id)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public UserProfile insert(UserProfile userProfile) {
        return this.userProfileDao
                .save(userProfile);
    }

    @Override
    public UserProfile update(long id, UserProfile updatedUserProfile) {
        UserProfile userProfile = this.getByUserEntityId(id);

        userProfile.setName(updatedUserProfile.getName());
        userProfile.setSurname(updatedUserProfile.getSurname());
        userProfile.setAddress(updatedUserProfile.getAddress());
        userProfile.setEmail(updatedUserProfile.getEmail());
        userProfile.setPhone(updatedUserProfile.getPhone());

        return this.userProfileDao.save(userProfile);


    }
}
