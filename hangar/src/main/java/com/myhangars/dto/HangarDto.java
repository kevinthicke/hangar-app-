package com.myhangars.dto;

import javax.validation.constraints.NotBlank;

public class HangarDto {

    private long id;

    @NotBlank(message = "Hangar name cannot be empty")
    private String name;

    @NotBlank(message = "Hangar location cannot be empty")
    private String location;

    @NotBlank(message = "Hangar owner cannot be empty")
    private String owner;

    @NotBlank(message = "hangar email cannot be empty")
    private String email;

    @NotBlank(message = "Hangar phone cannot be empty")
    private String phone;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
