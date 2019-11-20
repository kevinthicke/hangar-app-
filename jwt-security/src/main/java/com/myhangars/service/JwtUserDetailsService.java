package com.myhangars.service;

import com.myhangars.exception.GenericException;
import com.myhangars.model.UserEntity;
import com.myhangars.model.UserProfile;
import com.myhangars.user.builder.UserEntityBuilder;
import com.myhangars.user.dao.UserEntityDao;
import com.myhangars.user.dto.UserDto;
import com.myhangars.user_profile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
//@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired private PasswordEncoder bcryptEncoder;

    @Autowired private UserEntityDao userEntityDao;

    @Autowired private UserProfileService userProfileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userEntityDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public UserEntity save(UserDto userDto) throws Exception {

        String encryptedPassword = bcryptEncoder.encode(userDto.getPassword());
        userDto.setPassword(encryptedPassword);

        boolean isUsernameUnique = ! this.userEntityDao.existsByUsername(userDto.getUsername());

        if(!isUsernameUnique) {
            throw new GenericException.AlreadyExists(userDto.getUsername());
        }

        UserEntity userEntity = new UserEntityBuilder(userDto).getUserEntity();

        UserProfile userProfile = new UserProfile();
        userEntity.setProfile(userProfile);

        return this.userEntityDao.save(userEntity);
    }
}