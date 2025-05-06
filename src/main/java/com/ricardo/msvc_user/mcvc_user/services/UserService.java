package com.ricardo.msvc_user.mcvc_user.services;

import com.ricardo.msvc_user.mcvc_user.dto.UserRequestDTO;
import com.ricardo.msvc_user.mcvc_user.dto.UserResponseDTO;
import com.ricardo.msvc_user.mcvc_user.entities.User;

import java.util.List;

public interface UserService {


    List<UserResponseDTO> getUsers();

    UserResponseDTO createUser(UserRequestDTO user);

    Void deleteUser(String id);

    UserResponseDTO updateUser(String username, UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(String username);

}
