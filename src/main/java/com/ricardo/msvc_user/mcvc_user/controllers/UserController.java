package com.ricardo.msvc_user.mcvc_user.controllers;

import com.ricardo.msvc_user.mcvc_user.dto.UserRequestDTO;
import com.ricardo.msvc_user.mcvc_user.dto.UserResponseDTO;
import com.ricardo.msvc_user.mcvc_user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/*
* Se podria agregar una libreria de validacion de los campos de entrada
* */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    public UserController() {
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        long startTime = System.currentTimeMillis();
        System.out.println("HELLO");
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestPart("user") UserRequestDTO userRequestDTO,
            @RequestPart(value = "image" ,required = false) MultipartFile profileImage) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO,profileImage);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){

        try{
            userService.deleteUser(username);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Usuario eliminado correctamente",
                    "status", HttpStatus.OK.value()
            ));
        } catch (Exception e) {
            throw e;
        }
    }


    @PutMapping("/{username}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable String username,
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updatedUser = userService.updateUser(username, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String username){
        UserResponseDTO dto = userService.getUserById(username);
        return ResponseEntity.ok(dto);
    }
}
