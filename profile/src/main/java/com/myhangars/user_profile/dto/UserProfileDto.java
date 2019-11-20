package com.myhangars.user_profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private long id;

    @NotEmpty @NotBlank @Size(min = 3)
    private String name;

    @NotEmpty @NotBlank @Size(min = 3)
    private String surname;

    @NotEmpty
    private String address;


    @NotEmpty @Email
    private String email;

    private String phone;

}
