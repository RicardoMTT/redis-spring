package com.ricardo.msvc_user.mcvc_user.services;

import com.ricardo.msvc_user.mcvc_user.UserNotFoundException;
import com.ricardo.msvc_user.mcvc_user.dto.UserRequestDTO;
import com.ricardo.msvc_user.mcvc_user.dto.UserResponseDTO;
import com.ricardo.msvc_user.mcvc_user.entities.User;
import com.ricardo.msvc_user.mcvc_user.mappers.UserMapper;
import com.ricardo.msvc_user.mcvc_user.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    private static final String CACHE_NAME = "users";
    private static final String ALL_USERS_CACHE_KEY = "all";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = CACHE_NAME, key = "'all'")
    public List<UserResponseDTO> getUsers() {

        logger.info("Obteniendo todos los usuarios de la base de datos");
        List<User> users = userRepository.findAll();//Esto incluye el password

        // Sera un listado de tipo UserResponseDTO que no tendra el campo password
        List<UserResponseDTO> usersResponse = userMapper.toResponseDtoList(users);

        return usersResponse;
    }




    @CacheEvict(value = CACHE_NAME, key = "'all'")
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (userRequestDTO.getName() == null || userRequestDTO.getName().isEmpty()
                || userRequestDTO.getLastnamefather() == null ||
                userRequestDTO.getLastnamefather().isEmpty()) {
            throw new IllegalArgumentException("El nombre y apellido paterno son obligatorios");
        }

        //Logica para completar en el campo username que sera un campo unico


        // Primera opción: primera letra del nombre + apellido paterno completo
        char firstLetter = userRequestDTO.getName().charAt(0);

        String lastNameFather = userRequestDTO.getLastnamefather();

        String username = firstLetter + lastNameFather;


        // Verificar si el username ya existe
        if (userRepository.existsById(username)) {
            // Segunda opción: primera letra del nombre + apellido paterno completo + primera letra del apellido materno
            String lastNameMother = userRequestDTO.getLastnameMother();
            if (lastNameMother != null && !lastNameMother.isEmpty()) {
                char motherFirstLetter = lastNameMother.charAt(0);
                username = firstLetter + lastNameFather + motherFirstLetter;

                // Verificar si el segundo formato de username también existe
                if (userRepository.existsById(username)) {
                    // Tercera opción: agregar un número incremental al final
                    int counter = 1;

                    String baseUsername = username;
                    username = baseUsername + counter;
                    // Incrementar el contador hasta encontrar un username disponible
                    while (userRepository.existsById(username)) {
                        counter++;
                        username = baseUsername + counter;
                    }
                }
            }else{
                // Si no hay un apellido materno usar el contador
                int counter = 1;
                String baseUsername = username;
                username = baseUsername + counter;
                // Incrementar el contador hasta encontrar un username disponible
                while (userRepository.existsById(username)) {
                    counter++;
                    username = baseUsername + counter;
                }
            }
        }


        // Verificar si el username ya existe
        //if (userRepository.existsById(stringFinal)) {
        //    //Spring busca un método anotado con @ExceptionHandler(UserAlreadyExistsException.class).
        //    throw new UserAlreadyExistsException("El username ya existe");//Busca un manejador para el tipo UserAlreadyExistsException
        //}

        // Convertir DTO a entidad
        User user = userMapper.toEntity(userRequestDTO);
        user.setUsername(username);
        // Guardar usuario en la base de datos
        User savedUser = userRepository.save(user);


        // Convertir entidad a DTO de respuesta
        return userMapper.toResponseDto(savedUser);

    }

    @Override
    public Void deleteUser(String id) {
        if (!userRepository.existsById(id.toString())){
            throw new UserNotFoundException(id,"usuario");
        }
        this.userRepository.deleteById(id.toString());

        return null;
    }

    @Override
    public UserResponseDTO updateUser(String username, UserRequestDTO userRequestDTO) {
        // Buscar usuario por username
        Optional<User> user = userRepository.findById(username);

        if (user.isEmpty()){
            throw new UserNotFoundException("usuario no encontrado");
        }

        // Pasamos el user al mapper para actualizarlo con los valores del dto
        // esta funcion actualiza el user, una vez termine y vuelve al servicio
        // lo que se ejecute abajo sera con el user actualizado
        userMapper.updateUserFromDto(userRequestDTO, user.get());

        // Aca tambien deberia generan un nuevo username ?
        // ya que este es nuestra key y esta se genera en base al name,lastnamefather,lastnamemother
        // que se envia en la solicitud

        // Guardar usuario actualizado producto de la funcion updateUserFromDto
        User updatedUser = userRepository.save(user.get());

        return userMapper.toResponseDto(updatedUser);
    }

    @Override
    public UserResponseDTO getUserById(String username) {
        Optional<User> user = userRepository.findById(username);

        if (user.isEmpty()){
            throw new UserNotFoundException("usuario no encontrado");
        }

        return userMapper.toResponseDto(user.get());
    }

}
