package com.ricardo.msvc_user.mcvc_user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String name;
    private String lastnamefather;
    private String lastnameMother;
    private boolean status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastnamefather() {
        return lastnamefather;
    }

    public void setLastnamefather(String lastnamefather) {
        this.lastnamefather = lastnamefather;
    }

    public String getLastnameMother() {
        return lastnameMother;
    }

    public void setLastnameMother(String lastnameMother) {
        this.lastnameMother = lastnameMother;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
