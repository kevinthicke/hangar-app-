package com.myhangars.user.dao;

import com.myhangars.model.UserEntity;
import com.myhangars.user.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEntityDao {

    @Autowired private UserEntityRepository userEntityRepository;

    public Optional<UserEntity> findById(long id) {
        return this.userEntityRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return this.userEntityRepository.findByUsername(username);
    }

    public UserEntity save(UserEntity userEntity) {
        return this.userEntityRepository.save(userEntity);
    }

    public boolean existsByUsername(String username) {
        return this.userEntityRepository.existsByUsername(username);
    }


}
