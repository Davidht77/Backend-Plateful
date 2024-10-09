[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/9LreXiV7)

# ENTREGABLE DE PROYECTO: PLATEFUL


![Logo de Plateful](./images/_593fd117-ce1f-4fd5-919e-9da2321a4f86.jpeg "Logo de Plateful")

---

## Curso de Desarrollo Basado en Plataformas

## Integrantes:
| Nombre                     
|----------------------------
| Diego Alexis Gil Rojas 
| Marco Apolinario Lainez   
| David Huette Ospino
| Anel Rojas Martinez

---

## Indice
# Table of Contents
- [Introducción](#introducción)
   1. [Contexto del proyecto](#1-contexto-del-proyecto)
   2. [Objetivos](#2-objetivosobjetivos)
- [Identificación del Problema o Necesidad](#identificación-del-problema-o-necesidad)
   3. [Descripcion del problema](#3-descripcion-del-problema)
   4. [Justificación del problema](#4-justificación-del-problema)
- [Descripción de la Solución](#descripción-de-la-solución)
   5. [Funcionalidades Clave](#5-funcionalidades-clave)
   6. [Tecnologias Usadas](#6-tecnologias-usadas)
- [Modelo de Entidades](#modelo-de-entidades)
   7. [Diagrama Entidad-Relacion](#7-diagrama-entidad-relacion)
   8. [Descripcion de Entidades](#8-descripcion-de-entidades)
      - [Entidades básicas identificadas](#entidades-básicas-identificadas)
      - [Atributos](#atributos)
      - [Relaciones](#relaciones)
- [Testing y Manejo de Errores](#testing-y-manejo-de-errores)
   9. [Niveles de Testing Realizados](#9-niveles-de-testing-realizados)
   10. [Resultados](#10-resultados)
   11. [Manejo de Errores](#11-manejo-de-errores)
- [Medidas de Seguridad Implementadas](#medidas-de-seguridad-implementadas)
   12. [Seguridad de Datos](#12-seguridad-de-datos)
   13. [Prevencion de Vulnerabilidades](#13-prevencion-de-vulnerabilidades)
- [Eventos y Asincronía](#eventos-y-asincronia)
   14. [Descripcion de Eventos](#14-descripcion-de-eventos)
- [GitHub](#github)
   15. [Utilizacion de Github Projects](#15-utilizacion-de-github-projects)
   16. [Utilizacion de Github Actions](#16-utilizacion-de-github-actions)
- [Conclusión](#conclusion)
   17. [Logros del Projecto](#17-logros-del-projecto)
   18. [Aprendizajes Clave](#18-aprendizajes-clave)
   19. [Trabajo Futuro](#19-trabajo-futuro)
- [Apéndices](#apendices)
   20. [Licencia](#20-licencia)
   21. [Referencias](#21-referencias)

---

## Introducción

### 1. Contexto del proyecto:


### 2. Objetivos:

- Facilitar la conexión entre clientes y restaurantes en una misma zona.
- Permitir que los usuarios puedan ver menús, precios y disponibilidad de platos en tiempo real.
- Ofrecer a los restaurantes una plataforma para gestionar sus menús, recibir reseñas y responder a las críticas de los clientes.
- Fomentar la interacción entre restaurantes y clientes mediante un foro de comentarios.
- Crear un sistema de evaluación de restaurantes por parte de los usuarios, permitiendo calificaciones de 1 a 5 estrellas.

---
## Identificación del Problema o Necesidad

### 3. Descripcion del problema:
En un entorno donde las opciones para comer son cada vez más diversas y donde la gente busca experiencias de comida cercanas y accesibles, surge la necesidad de conectar a los clientes con los restaurantes locales de una manera simple y eficiente.

**Plateful** busca ser una solución tanto para los clientes que desean descubrir los mejores restaurantes cercanos a su ubicación, como para los restaurantes que desean ofrecer sus menús y recibir retroalimentación directa de sus clientes. La plataforma permitirá a los usuarios evaluar y comentar sobre los restaurantes, así como a los restaurantes gestionar su oferta gastronómica de forma dinámica.


### 4. Justificación del problema

Con la creciente oferta de restaurantes y opciones de comida, las personas a menudo no tienen la información suficiente para elegir dónde comer, basándose en criterios como proximidad, menú y opiniones de otros clientes.

Por otro lado, muchos restaurantes carecen de una plataforma fácil de usar para compartir su oferta actualizada y recibir retroalimentación. **Plateful** ofrece una solución a ambos problemas, conectando a clientes y restaurantes en una misma aplicación.

---
## Descripción de la Solución

### 5. Funcionalidades Clave

- **Registro de usuarios**: Los clientes y restaurantes podrán registrarse en la plataforma seleccionando su rol.
- **Localización geográfica**: Los usuarios podrán visualizar los restaurantes cercanos a su ubicación actual, que estén registrados en la app.
- **Visualización de menús**: Los restaurantes podrán subir sus menús, con precios y disponibilidad de platos actualizados en tiempo real.
- **Evaluación de restaurantes**: Los clientes podrán puntuar los restaurantes de 1 a 5 estrellas después de cada visita.
- **Foro de comentarios**: Los restaurantes podrán interactuar con los clientes respondiendo a sus críticas o agradeciendo sus reseñas.
- **Gestión de restaurantes**: Los restaurantes tendrán una sección para gestionar sus menús, horarios y precios.


### 6. Tecnologias Usadas

---

## Modelo de entidades

### 7. Diagrama Entidad-Relacion


![Logo de Plateful](./images/ER-PROYECTO-DBP.drawio.png "Logo de Plateful")

### 8. Descripcion de Entidades

#### Entidades básicas identificadas

- **Usuario**: 
  - Clientes o dueños
  - Posee un `id` (PK) al momento del registro
  - Solicita un `nombre`, `correo`, una `contraseña` y el `rol`.

- **Restaurante**:
  - Se selecciona al registrarse como dueños
  - Posee un `id` (PK)
  - Al momento del registro pedirá un `nombre_restaurante`, la `ubicación`, `horario de atención` y el `propietario` (FK de Usuario)
  - Se solicitará una `carta` y cada restaurante podrá recibir una `reseña` junto con `comentarios`.

- **Reseña**:
  - Establecida por un usuario de tipo cliente
  - Se expresa en una `calificación` del 1 al 5 (representada por estrellas)
  - Posee un `id_reseña` (PK) y es establecida por un `id_usuario` (FK) y repercute en un `id_restaurante` (FK)
  - También se establece una media de calificación entre la cantidad de usuarios que califiquen.

- **Carta**:
  - Espacio para la gestión y visibilidad de `Platos`
  - El dueño crea su propia carta que posee un `id` único (`id_carta` PK)
  - Se le podrá agregar un `nombre` y su `última fecha de actualización`.

- **Plato**:
  - Dependiendo de la carta creada (`id_carta` FK Carta), se agregarán los platos que hay en la misma
  - Los platos tendrán un `id` único (`id_plato`)
  - Se le podrá asignar un `nombre` al plato
  - También se crea un atributo `disponibilidad` (para saber si aún no se ha acabado el plato).

- **Comentarios**:
  - Entidad creada para que el `Usuario` pueda dejar sus comentarios en alguna `Reseña`
  - Cada comentario será guardado con un `id` único
  - Tendrán su `contenido` y la `fecha de publicación`.

### Atributos

#### Usuario
- `id_usuario` (PK)
- `fecha_nacimiento`
- `nombre`
- `correo`
- `contraseña`
- `telefono`

#### Cliente
- `Usuario.id_usuario` (PK FK a `id_usuario`)
- `ubicacionActual` (FK a `id_ubicacion`)

#### Ubicacion
- `id_ubicacion`
- `latitud`
- `longitud`
- `ciudad`
- `direccionCompleta`
- `codigoPostal`

#### Propietario
- `Usuario.id_usuario` (PK FK)
- `tipoDocumento`
- `numeroDocumento`
- `fotoPerfil`

#### Restaurante
- `id_restaurante` (PK)
- `nombre_restaurante`
- `ubicación`
- `horario`
- `propietario` (FK de a `Propietario.id_usuario`)
- `tipoRestaurante` (Menú/Chifa/Comida Rápida/etc.)
- `calificación_promedio`

#### Reseña
- `id_reseña` (PK)
- `calificación` (1 a 5 estrellas)
- `id_usuario` (FK de `Usuario`)
- `id_restaurante` (FK de `Restaurante`)
- `fecha`

#### Carta
- `id_carta` (PK)
- `id_restaurante` (FK `Restaurante`)
- `nombre`
- `fecha_actualizacion`

#### Plato
- `id_plato` (PK)
- `id_carta` (FK `Carta`)
- `nombre`
- `precio`
- `disponibilidad`

#### Comentario
- `id_comentario` (PK)
- `id_reseña` (FK `Reseña`)
- `id_usuario` (FK `Usuario`)
- `contenido`
- `fecha`

#### Relaciones (FALTA ARREGLAR)

- Un Usuario puede ser cliente o restaurante: Se manejan a través del atributo `rol` en la entidad Usuario.
- Un Restaurante pertenece a un Usuario: Relación one-to-one entre `Usuario` y `Restaurante` donde `id_usuario` en `Restaurante` es FK que referencia a `Usuario`.
- Un Restaurante tiene una carta: Relación one-to-one entre `Restaurante` y `Carta` donde `id_restaurante` es FK en `Carta`.
- Un Menú tiene varios Platos: Relación one-to-many entre `Carta` y `Plato` donde `id_carta` es FK en `Plato`.
- Un Usuario puede hacer varias reseñas de diferentes Restaurantes: Relación one-to-many entre `Usuario` y `Reseña` donde `id_usuario` es FK en `Reseña`.
- Un Restaurante puede recibir varias reseñas de diferentes Usuarios: Relación one-to-many entre `Restaurante` y `Reseña` donde `id_restaurante` es FK en `Reseña`.
- Una Reseña puede tener varios Comentarios: Relación one-to-many entre `Reseña` y `Comentario` donde `id_reseña` es FK en `Comentario`.
- Un Usuario puede hacer varios Comentarios en diferentes Reseñas: Relación one-to-many entre `Usuario` y `Comentario` donde `id_usuario` es FK en `Comentario`.

---

## Testing y Manejo de Errores

### 9. Niveles de Testing Realizados
Describir los niveles de prueba (por ejemplo, unitarias, integración, sistema, aceptación) realizados para asegurar la calidad del software.

### 10. Resultados
Resumir los resultados de las pruebas, incluyendo los principales errores o fallos encontrados y corregidos

### 11. Manejo de Errores
Explicar en términos generales las excepciones globales utilizadas y por qué se deben manejar

---

## Medidas de Seguridad Implementadas

### 12. Seguridad de Datos
Explicar las técnicas y mecanismos adoptados para garantizar la seguridad de los datos (por ejemplo, cifrado, autenticación, gestión de permisos).

### 13. Prevencion de Vulnerabilidades
Describir las medidas tomadas para prevenir vulnerabilidades comunes (por ejemplo, inyección SQL, XSS, CSRF)

---

## Eventos y Asincronia

### 14. Descripcion de Eventos
Detallar los eventos utilizados, explicar la importancia de su implementación en su proyecto, así como exponer el porqué deben ser asincrónicos

---

## GitHub

### 15. Utilizacion de Github Projects
Describir la manera en que se usó GitHub projects (asignación de issues, deadlines, etc)

### 16. Utilizacion de Github Actions
Describir el uso de GitHub Actions y el flujo que implementaron para su proyecto en particular

---

## Conclusion

### 17. Logros del Projecto
Resumir los logros alcanzados con el proyecto en términos de resolver el problema o satisfacer la necesidad identificada.

### 18. Aprendizajes Clave
Reflexionar sobre los aprendizajes más significativos obtenidos durante el desarrollo del proyecto

### 19. Trabajo Futuro
Sugerir posibles mejoras o extensiones para el proyecto

---

## Apendices

### 20. Licencia
Especificar la licencia bajo la cual se distribuye el proyecto

### 21. Referencias

---