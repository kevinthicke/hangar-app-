package com.myhangars.user.service;

import com.myhangars.config.JwtTokenUtil;
import com.myhangars.exception.GenericException;
import com.myhangars.model.UserEntity;
import com.myhangars.user.dao.UserEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityDao userEntityDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserEntity findById(long id) {
        return this.userEntityDao.findById(id)
                .orElseThrow(() -> new GenericException.NotFound(id));
    }


    @Override
    public UserEntity getByUsername(String username) {
        return this.userEntityDao.findByUsername(username)
                .orElseThrow(() -> new GenericException.NotFound(username));
    }

    @Override
    public UserEntity getUserEntityId(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return this.getByUsername(username);
    }
}
