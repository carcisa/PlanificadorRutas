## Enlace a colección de Postman
[Postman](https://documenter.getpostman.com/view/32189041/2sA2rGwKgP)

# Definición de Entidades

**Entidades:**

- **Atracción:** Representa lugares de interés dentro de un destino. Atributos incluyen ID, nombre, descripción, categoría, dirección, y destino asociado.
- **Destino:** Define ubicaciones que los usuarios pueden explorar. Incluye ID, nombre, descripción, y una lista de atracciones.
- **Usuario:** Almacena información de los usuarios, con atributos como ID, nombre de usuario (email), contraseña, y roles para control de acceso.
  
 [Diagrama ER](https://drive.google.com/file/d/1u3MFC_jUkMNCPc2Y3oPtk-zUDk0g5mYE/view?usp=sharing)

## Relaciones entre Entidades

- Un **Usuario** puede seleccionar múltiples **Destinos**.
- Un **Destino** contiene múltiples **Atracciones**.
- **Atracciones** están categorizadas y pueden ser seleccionadas para formar un plan de ocio.


# Funcionalidades y API Endpoints

## Autenticación

- **Login de usuario:** POST `http://localhost:8081/authenticate/signin`
  - Body: email y contraseña usuario
- **Login de admin:** POST `http://localhost:8081/authenticate/signin`
  - Body: email y contraseña admin

## CRUD para Destinos, Usuarios, y Atracciones

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

### ATRACCIONES

- LISTAR-GET: `http://localhost:8081/api/atracciones/`
- LISTAR POR ID- GET: `http://localhost:8081/api/atracciones/{{id}}`
- NUEVA ATRACCIÓN-POST: `http://localhost:8080/api/atracciones`
- ACTUALIZAR – PUT: `http://localhost:8081/api/atracciones/{{id}}`
- BORRAR – DELETE: `http://localhost:8081/api/atracciones/{{id}}`

## Relaciones y Lógica de Negocio

- Listar atracciones por destino (GET `/destinos/{id}/atracciones`)
- Añadir atracción por destino (POST `/destinos/{id}/atracciones`)


## Diagramas

- **Diagrama de Entidad-Relación (ER):** Para visualizar las entidades y sus relaciones.
![Diagrama ER](https://drive.google.com/file/d/1hUhXwTkyBtyMxxrgeQftaPUANLEojDD9/view?usp=sharing)
  


- **Diagramas de Clases:** Para el diseño de la estructura de clases que se implementará en Spring Boot.
![Diagrama ER](https://drive.google.com/file/d/1j6e6geeHSSqB5zSsdetImg_hA4hjaaD5/view?usp=sharing)



- **Diagrama de Secuencia:** Para mostrar cómo interactúan las diferentes partes del sistema durante una operación típica, como la sugerencia de planes de ocio.
  
![Diagrama ER](https://drive.google.com/file/d/1Xto0YVP_WQnAOc7d-pVKhr2gEZnkS6O6/view?usp=sharing)


# Lógica de Negocio Adicional
- Sugerir planes de ocio combinando atracciones de diferentes categorías por destino (GET `/destinos/{id}/planes`)
- Implementación de seguridad y autenticación, posiblemente usando JWT para manejar sesiones de usuario y admin.
- Algoritmos para sugerir planes de ocio, considerando las preferencias del usuario y la disponibilidad de atracciones en cada categoría.
- Algoritmos para sugerir planes de ocio, considerando un presupuesto sugerido por el usuario.
- Implementación de opiniones y puntuación a destinos y atracciones.
- Implementación de una mini red social donde los usuarios puedan hacer planes en conjunto.
