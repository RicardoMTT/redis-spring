package com.ricardo.msvc_user.mcvc_user.repositories;

import com.ricardo.msvc_user.mcvc_user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// Mi interfaz hereda los metodos de CrudRepository
// El repositorio gestionara registros de la entidad User
public interface UserRepository extends JpaRepository<User,String> {
}
