package com.ricardo.msvc_user.mcvc_user;

import com.ricardo.msvc_user.mcvc_user.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@ControllerAdvice//Intercepta todas las excepciones lanzadas
    public class GlobalExceptionHandler {

    // ExceptionHandler define qué tipos de excepciones maneja cada método.
    // Es una anotacion que marca un metodo como responsable de procesar excepciones.

    /*
    * Esta anotación indica a Spring que este método debe ser invocado cuando
    * se lance  una excepción del tipo especificado entre paréntesis(UserAlreadyExistsException).
    * */
    @ExceptionHandler(UserAlreadyExistsException.class)// Para que escepciones se ejecutara este metodo
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "USER_ALREADY_EXISTS",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "USER_NOT_FOUND",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Manejador para excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Se produjo un error en el servidor",
                "INTERNAL_SERVER_ERROR",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
