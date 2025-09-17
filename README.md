# 📌 Uso de Redis en una API Spring Boot

Este proyecto utiliza **Redis** como sistema de caché para mejorar el rendimiento de la API y evitar consultas repetitivas a la base de datos.  
Redis se ejecuta en un **contenedor Docker**, y la aplicación Spring Boot se conecta a él mediante **Spring Data Redis**.

---

## 🚀 ¿Qué es Redis?
[Redis](https://redis.io/) es una base de datos en memoria, rápida y ligera, utilizada comúnmente como:
- Caché de datos
- Almacenamiento de sesiones
- Cola de mensajes
- Key-Value Store

En este proyecto, Redis se usa como **caché para datos de usuarios**.

---

## 🐳 Levantar Redis con Docker

Asegúrate de tener **Docker instalado** y luego ejecuta:

```bash
docker-compose up -d
```

Ahora ejecuta: 
```bash
docker exec -it redis-spring-redis-1
```
Siendo redis-spring-redis-1 el nombre del contenedor

Ahora , si quieres entrar al contenedor ejecuta:

```bash
docker exec -it redis-spring-redis-1 redis-cli
```

Una vez en el contenedor puedes ejecutar:

```bash
 GET users::all
```
Siendo users::all la key
