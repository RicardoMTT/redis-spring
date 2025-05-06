# mcvc-user

Este es un proyecto de demostración de Spring Boot que utiliza Redis para el almacenamiento en caché y MySQL para la persistencia de datos. La aplicación gestiona información de usuarios, permitiendo crear, leer, actualizar y eliminar registros.

## Tecnologías Utilizadas

* **Spring Boot:** Framework de Java para la creación de aplicaciones independientes y de nivel empresarial basadas en Spring.
* **Spring Data JPA:** Facilita el acceso y la persistencia de datos entre la aplicación Java y la base de datos MySQL.
* **MySQL:** Sistema de gestión de bases de datos relacional.
* **Spring Data Redis:** Proporciona una fácil integración con el almacén de datos en memoria Redis.
* **Spring Cache:** Abstracción para la gestión de caché, con soporte para diferentes proveedores como Redis.
* **Lettuce:** Cliente Redis avanzado, no bloqueante y reactivo para Java.
* **Lombok:** Biblioteca Java que reduce la cantidad de código boilerplate (getters, setters, constructores, etc.) mediante anotaciones.
* **Maven:** Herramienta de gestión de proyectos y construcción.

## Configuración

### Requisitos Previos

Asegúrate de tener instalado lo siguiente en tu entorno de desarrollo:

* **Java Development Kit (JDK) 17 o superior:** Necesario para compilar y ejecutar la aplicación Spring Boot.
* **Maven:** Utilizado para la gestión de dependencias y la construcción del proyecto.
* **MySQL:** Una instancia de MySQL en ejecución para la persistencia de datos.
* **Redis:** Una instancia de Redis en ejecución para el almacenamiento en caché.

### Configuración de la Aplicación

1.  **Clonar el repositorio (si aplica).**
2.  **Configurar la base de datos MySQL:**
    * Crea una base de datos para la aplicación.
    * Asegúrate de que las credenciales de acceso a la base de datos (usuario, contraseña, URL) estén configuradas correctamente en el archivo `src/main/resources/application.properties` o `application.yml`. Por ejemplo:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos?serverTimezone=UTC
        spring.datasource.username=tu_usuario_mysql
        spring.datasource.password=tu_contraseña_mysql
        spring.jpa.hibernate.ddl-auto=update
        ```
3.  **Configurar Redis:**
    * Verifica que la dirección del host y el puerto de tu instancia de Redis sean correctos en el archivo `src/main/resources/application.properties` o `application.yml`. Por ejemplo:
        ```properties
        spring.redis.host=localhost
        spring.redis.port=6379
        ```

## Ejecución de la Aplicación

1.  **Navega al directorio raíz del proyecto** en tu terminal.
2.  **Ejecuta el comando de Maven para construir y ejecutar la aplicación:**
    ```bash
    ./mvnw spring-boot:run
    ```
    o si no tienes el wrapper de Maven:
    ```bash
    mvn spring-boot:run
    ```
3.  **La aplicación estará disponible en `http://localhost:8080` (puerto por defecto).**

## Endpoints de la API

La API de usuarios proporciona los siguientes endpoints:

* **`GET /api/users`:** Obtiene una lista de todos los usuarios (la información se recupera de la caché si está disponible).
* **`POST /api/users`:** Crea un nuevo usuario. Requiere un cuerpo JSON con los campos `name`, `lastnamefather` (obligatorios) y opcionalmente `lastnamemother`. Genera un `username` único basado en estos campos.
* **`GET /api/users/{username}`:** Obtiene un usuario específico por su `username` (la información se recupera de la caché si está disponible).
* **`PUT /api/users/{username}`:** Actualiza la información de un usuario existente identificado por su `username`. Requiere un cuerpo JSON con los campos a actualizar.
* **`DELETE /api/users/{username}`:** Elimina un usuario existente identificado por su `username`.

## Caching con Redis

Esta aplicación utiliza Redis para mejorar el rendimiento mediante el almacenamiento en caché de los datos de los usuarios.

* La lista de todos los usuarios obtenida a través de `GET /api/users` se almacena en caché con la clave `'all'` bajo el espacio de nombres `'users'`. La información se mantiene en caché durante 2 minutos.
* La información de un usuario individual obtenida a través de `GET /api/users/{username}` también se almacena en caché bajo el espacio de nombres `'users'` y la clave correspondiente al `username`.
* Las operaciones de creación (`POST /api/users`), actualización (`PUT /api/users/{username}`) y eliminación (`DELETE /api/users/{username}`) invalidan la caché de todos los usuarios (`'all'`) para asegurar la consistencia de los datos.

## Manejo de Errores

La aplicación incluye manejo de errores básico, como `UserNotFoundException` cuando se intenta acceder o modificar un usuario que no existe y `IllegalArgumentException` cuando se intentan crear usuarios sin los campos obligatorios.

## Próximas Mejoras

* Implementación de validación de datos más robusta utilizando Bean Validation.
* Adición de pruebas unitarias e de integración.
* Configuración de un sistema de logging más detallado.
* Posibilidad de configurar el tiempo de vida (TTL) de la caché de forma más granular.
* Implementación de paginación para la obtención de grandes cantidades de usuarios.
