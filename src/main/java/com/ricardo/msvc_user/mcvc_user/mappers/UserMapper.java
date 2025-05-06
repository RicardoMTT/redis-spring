package com.ricardo.msvc_user.mcvc_user.mappers;

import com.ricardo.msvc_user.mcvc_user.dto.UserDTO;
import com.ricardo.msvc_user.mcvc_user.dto.UserRequestDTO;
import com.ricardo.msvc_user.mcvc_user.dto.UserResponseDTO;
import com.ricardo.msvc_user.mcvc_user.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserMapper {


    // Entity to DTO mappings
    public UserDTO toDto(User user){
        if (user == null){
            return  null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setStatus(user.isStatus());
        userDTO.setLastnamefather(user.getLastnamefather());
        userDTO.setLastnameMother(user.getLastnameMother());

        return userDTO;
    }

    // Convertir Entity a DTO, es lo que se expondra al publico
    public UserResponseDTO toResponseDto(User user){
        if (user == null){
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setName(user.getName());
        userResponseDTO.setLastnamefather(user.getLastnamefather());
        userResponseDTO.setLastnameMother(user.getLastnameMother());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setStatus(user.isStatus());

        return userResponseDTO;
    }

    public List<UserResponseDTO> toResponseDtoList(List<User> users){
        if (users == null){
            return null;
        }

        List<UserResponseDTO> responseDTOs = new ArrayList<>(users.size());
        for (User user : users) {
            responseDTOs.add(toResponseDto(user));
        }

        return responseDTOs;
    }

    // DTO to Entity mappings
    //User toEntity(UserDTO userDTO);

    // Convert DTO to Entity, esto es lo que se guardara en la bd
    // aca userRequestDTO no tiene el campo username por que ese campo
    // No se setea de inmediato sino que se autogenera en base
    // a los campos name,lastNameFather,lastNameMother
    public User toEntity(UserRequestDTO userRequestDTO){
        if (userRequestDTO == null){
            return null;
        }

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setLastnamefather(userRequestDTO.getLastnamefather());
        user.setLastnameMother(userRequestDTO.getLastnameMother());
        user.setStatus(user.isStatus());
        user.setPassword(user.getPassword());

        return user;

    }

    public void updateUserFromDto(UserRequestDTO userRequestDTO,User user){
        if (userRequestDTO == null || user == null) {
            return;
        }

        // Actualizamos los campos pero ignoramos id y createdAt según las anotaciones

        user.setName(userRequestDTO.getName());
        user.setStatus(userRequestDTO.isStatus());
        user.setLastnameMother(userRequestDTO.getLastnameMother());
        user.setLastnamefather(userRequestDTO.getLastnamefather());
        user.setPassword(userRequestDTO.getPassword());


    }

}
