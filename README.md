-----

# 📚 LiterAlura: Tu Biblioteca Personal en Consola 🚀

¡Bienvenido a LiterAlura\! Este proyecto es una aplicación de consola en Java diseñada para interactuar con la vasta colección de libros de la **API Gutendex**. Podrás buscar tus libros favoritos, registrarlos en una base de datos local y explorar estadísticas interesantes sobre ellos.

-----

## 🎯 Objetivo del Desafío

El principal objetivo de este desafío fue construir una aplicación robusta que permita a los usuarios:

  * 🔍 **Buscar libros** por título directamente desde la API Gutendex.
  * 💾 **Registrar y almacenar** la información de los libros y sus autores en una base de datos **Postgres**.
  * 📊 **Consultar y listar** la información almacenada de diversas maneras.

-----

## ✨ Características Principales

LiterAlura te ofrece las siguientes funcionalidades:

1.  **Buscar y Registrar Libros**:

    ```
    +-------------------+      +---------------------+      +-----------------+
    |  Usuario (Consola)| ---> |  Aplicación Java    | ---> |  API Gutendex   |
    +-------------------+      |   (Busca libro)     |      |   (Datos libro) |
                               +---------------------+      +--------+--------+
                                                                     |
                                                                     v
                                                          +---------------------+
                                                          |  Base de Datos      |
                                                          |  (Guarda libro/autor)|
                                                          +---------------------+
    ```

      * Busca un libro por su título en la API Gutendex.
      * Si el libro es encontrado, se guarda automáticamente en tu base de datos local, incluyendo la información del autor.
      * **Manejo de Duplicados**: ¡No te preocupes por guardar el mismo libro dos veces\! La aplicación previene inserciones duplicadas.
      * **Manejo de Errores**: Si el libro no se encuentra, recibirás un mensaje claro.

2.  **Listar Libros Registrados**:

    ```
    +-------------------+      +---------------------+
    |  Usuario (Consola)| ---> |  Aplicación Java    |
    +-------------------+      |   (Consulta DB)     |
                               +--------+------------+
                                        |
                                        v
                               +---------------------+
                               |  Base de Datos      |
                               |   (Lista libros)    |
                               +---------------------+
    ```

      * Visualiza todos los libros que has guardado en tu base de datos.

3.  **Listar Autores Registrados**:

      * Accede a una lista completa de todos los autores cuyos libros has registrado.

4.  **Listar Autores Vivos en un Año Específico**:

      * Filtra y muestra los autores que estaban vivos en un año determinado.

5.  **Listar Libros por Idioma**:

      * Encuentra fácilmente los libros registrados por un idioma específico (por ejemplo, español, inglés).

6.  **Estadísticas de Libros por Idioma**:

    ```
    +-------------------+      +---------------------+
    |  Usuario (Consola)| ---> |  Aplicación Java    |
    +-------------------+      |   (Procesa Datos)   |
                               +--------+------------+
                                        |
                                        v
                               +---------------------+
                               |  Base de Datos      |
                               |   (Todos los libros)|
                               +---------------------+
    ```

      * Obtén un resumen de cuántos libros tienes registrados por cada idioma, utilizando **Java Streams** para un procesamiento eficiente.

-----

## 🛠️ Herramientas y Tecnologías Utilizadas

Este proyecto fue desarrollado utilizando las siguientes herramientas y tecnologías:

  * **Java**: El lenguaje de programación principal.
      * Versión: **Java 17** ☕
  * **Spring Boot**: Un framework que facilita la creación de aplicaciones Java autónomas y robustas.
      * Versión: **3.5.3**
  * **PostgreSQL**: La base de datos relacional utilizada para almacenar la información de libros y autores. 🐘
  * **Spring Data JPA**: Simplifica la interacción con la base de datos, permitiendo un mapeo objeto-relacional (ORM) eficiente.
  * **API Gutendex**: La fuente de datos externa para buscar información de libros.
      * URL: [https://gutendex.com/](https://gutendex.com/) 🌐
  * **Spring Initializr**: Herramienta web para generar la estructura inicial del proyecto Spring Boot.
      * URL: [https://start.spring.io/](https://start.spring.io/) 🚀

-----

## ⚙️ Configuración Inicial del Proyecto

Para comenzar con este proyecto, la configuración inicial en Spring Initializr fue la siguiente:

  * **Tipo de Proyecto**: Maven Project
  * **Lenguaje**: Java
  * **Versión de Spring Boot**: 3.5.3
  * **Dependencias Añadidas**:
      * `Spring Data JPA`
      * `PostgreSQL Driver`

-----

¡Esperamos que disfrutes explorando y gestionando tu biblioteca con LiterAlura\! Si tienes alguna pregunta o sugerencia, no dudes en abrir un *issue*.
