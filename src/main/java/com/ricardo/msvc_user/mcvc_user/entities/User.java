package com.ricardo.msvc_user.mcvc_user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;


// Username,id,contraseña,estado,nombre de la persona
// Anotación incluye @Setter @Getter @ToString @EqualsAndHashCode @RequiredArgsConstructorß
@Entity
@Table(name = "users")

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    // Al momento de ingresar el username como pk por ejemplo se va a tomar la logica de que se tomara
    // la primera letra del campo name y el texto completo del campo lastnamefather
    // pero si ya existe alguien con esa combinacion entonces el nuevo usuario seria como la
    // concatenacion de la primera letra del name concatenado con el texto completo del campo lastnamefather
    // concatenado con la primera letra del campo lastnameMother , pero si aun asi ya existe
    // entonces el nuevo usuario seria como la concatenacion de la primera letra del name concatenado
    // con el texto completo del campo lastnamefather concatenado con la primera letra del campo lastnameMother
    // concatenado con un id , puede ser empezando con 1

    @Id
    private String username;

    private String name;

    private String lastnamefather;

    private String lastnameMother;

    private boolean status;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
