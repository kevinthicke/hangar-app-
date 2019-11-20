package com.myhangars.user.repository;

import com.myhangars.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long>{
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String name);
}
