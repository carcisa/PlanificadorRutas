## Enlace a colección de Postman
[Postman](https://documenter.getpostman.com/view/32189041/2sA2rGwKgP)

# Definición de Entidades

**Entidades:**

- **Actividad:** Representa lugares de interés dentro de un destino. Atributos incluyen ID, nombre, descripción, categoría, dirección, y destino asociado.
- **Destino:** Define ubicaciones que los usuarios pueden explorar. Incluye ID, nombre, descripción, y una lista de actividades.
- **Usuario:** Almacena información de los usuarios, con atributos como ID, nombre de usuario (email), contraseña, y roles para control de acceso.
  
[Diagrama ER](https://drive.google.com/file/d/1u3MFC_jUkMNCPc2Y3oPtk-zUDk0g5mYE/view?usp=sharing)

## Relaciones entre Entidades

- Un **Usuario** puede seleccionar múltiples **Destinos**.
- Un **Destino** contiene múltiples **Actividades**.
- **Actividades** están categorizadas y pueden ser seleccionadas para formar un plan de ocio.


# Funcionalidades y API Endpoints

## Autenticación

- **Login de usuario:** POST `http://localhost:8081/authenticate/signin`
  - Body: email y contraseña usuario
- **Login de admin:** POST `http://localhost:8081/authenticate/signin`
  - Body: email y contraseña admin

## CRUD para Destinos, Usuarios, y Actividades

### USUARIO

- LISTAR-GET: `http://localhost:8081/api/usuarios/`
- LISTAR POR ID- GET: `http://localhost:8081/api/usuarios/{{id}}`
- NUEVO USUARIO -POST: `http://localhost:8080/api/usuarios`
- ACTUALIZAR – PUT: `http://localhost:8081/api/usuarios/{{id}}`
- BORRAR – DELETE: `http://localhost:8081/api/usuarios/{{id}}`

### DESTINO

- LISTAR-GET: `http://localhost:8081/api/destinos/`
- LISTAR POR ID- GET: `http://localhost:8081/api/destinos/{{id}}`
- NUEVO DESTINO -POST: `http://localhost:8080/api/destinos`
- ACTUALIZAR – PUT: `http://localhost:8081/api/destinos/{{id}}`
- BORRAR – DELETE: `http://localhost:8081/api/destinos/{{id}}`

### ACTIVIDADES

- LISTAR-GET: `http://localhost:8081/api/actividades/`
- LISTAR POR ID- GET: `http://localhost:8081/api/actividades/{{id}}`
- NUEVA ACTIVIDAD-POST: `http://localhost:8080/api/actividades`
- ACTUALIZAR – PUT: `http://localhost:8081/api/actividades/{{id}}`
- BORRAR – DELETE: `http://localhost:8081/api/actividades/{{id}}`

## Relaciones y Lógica de Negocio

- Listar actividades por destino (GET `/destinos/{id}/actividades`)
- Añadir actividad por destino (POST `/destinos/{id}/actividades`)


## Diagramas

- **Diagrama de Entidad-Relación (ER):** Para visualizar las entidades y sus relaciones.
[Diagrama ER](https://drive.google.com/file/d/1hUhXwTkyBtyMxxrgeQftaPUANLEojDD9/view?usp=sharing)
  


- **Diagramas de Clases:** Para el diseño de la estructura de clases que se implementará en Spring Boot.
[Diagrama ER](https://drive.google.com/file/d/1j6e6geeHSSqB5zSsdetImg_hA4hjaaD5/view?usp=sharing)



- **Diagrama de Secuencia:** Para mostrar cómo interactúan las diferentes partes del sistema durante una operación típica,     como la sugerencia de planes de ocio.
[Diagrama ER](https://drive.google.com/file/d/1Xto0YVP_WQnAOc7d-pVKhr2gEZnkS6O6/view?usp=sharing)


# Lógica de Negocio Adicional
- Sugerir planes de ocio combinando actividades de diferentes categorías por destino (GET `/destinos/{id}/planes`)
- Implementación de seguridad y autenticación, posiblemente usando JWT para manejar sesiones de usuario y admin.
- Algoritmos para sugerir planes de ocio, considerando las preferencias del usuario y la disponibilidad de actividades en cada categoría.
- Algoritmos para sugerir planes de ocio, considerando un presupuesto sugerido por el usuario.
- Implementación de opiniones y puntuación a destinos y actividades.
- Implementación de una mini red social donde los usuarios puedan hacer planes en conjunto.
