package com.ricardo.msvc_user.mcvc_user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    // No tiene username, ya que este campo se genera en el service, ya que es un id
    private String name;
    private String lastnamefather;
    private String lastnameMother;
    private String password;
    private boolean status;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
