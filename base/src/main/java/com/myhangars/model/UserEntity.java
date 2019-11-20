package com.myhangars.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;


    @OneToOne(
            mappedBy = "userEntity",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private UserProfile userProfile;


    public void setProfile(UserProfile userProfile) {
        userProfile.setUserEntity(this);
        this.userProfile = userProfile;
    }

}
