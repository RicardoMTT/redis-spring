package com.ricardo.msvc_user.mcvc_user.services;

import com.ricardo.msvc_user.mcvc_user.dto.UserRequestDTO;
import com.ricardo.msvc_user.mcvc_user.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {


    List<UserResponseDTO> getUsers();

    // Nuevo método para paginación
    Page<UserResponseDTO> getUsersPaginated(int page, int size, String sortBy, String sortDir);

    UserResponseDTO createUser(UserRequestDTO user, MultipartFile multipartFile);

    Void deleteUser(String id);

    UserResponseDTO updateUser(String username, UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(String username);

}
