package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.models.Autor;
import com.aluracursos.literalura.models.DatosLibros;
import com.aluracursos.literalura.models.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.services.ConvierteDatos;
import com.aluracursos.literalura.services.ConsumoAPI;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner scanner = new Scanner(System.in);
    private String urlBase = "TU_URL_DE_LA_API"; // Reemplaza con la URL de la API que deseas consumir
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Mostrar el menu en consola
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    **************************************************
                        LiterAlura - Busqueda de Libros y Autores
                    **************************************************

                    Selecciona una opcion acontinuacion:

                    1 - Buscar un libro
                    2 - Consultar libros buscados
                    3 - Consultar autores
                    4 - Consultar autores de un año especifico
                    5 - Consultar libros por lenguaje
                    6 - Mostrar estadísticas de libros por idioma

                    0 - Salir
                    """;

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {

                System.out.println("Ingresa una opcion valida");
            }

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno();
                    break;
                case 5:
                    consultarLibrosLenguaje();
                    break;
                case 6:
                    mostrarEstadisticasLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Hasta pronto, gracias por usar LiterAlura");
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }
    }

    // Extrae los datos de un libro
    private DatosLibros getDatosLibro() {
        System.out.println("Ingrese el nombre del libro");
        var busqueda = scanner.nextLine().toLowerCase().replace(" ", "%20"); // Reemplaza espacios por %20 para la URL
        // Construye la URL de la API con la busqueda
        var json = consumoAPI.obtenerDatos(urlBase +
                "?search=" +
                busqueda); // Realiza la peticion a la API

        DatosLibros datosLibro = conversor.obtenerDatos(json, DatosLibros.class);
        return datosLibro; // Convierte el JSON a un objeto DatosLibros
    }

    // Busca un libro y guarda infromacion en la BD en sus tablas correspondientes
    private void buscarLibro() {
        DatosLibros datosLibro = getDatosLibro();

        try {
            Libro libro = new Libro(datosLibro.resultados().get(0)); // Extrae el primer libro de los resultados
            Autor autor = new Autor(datosLibro.resultados().get(0).autorList().get(0)); // Extrae el primer autor de los
                                                                                        // resultados

            System.out.println("""
                    libro[
                        titulo: %s
                        autor: %s
                        lenguaje: %s
                        descargas: %s
                    ]
                    """.formatted(libro.getTitulo(),
                    libro.getAutor(),
                    libro.getLenguaje(),
                    libro.getDescargas().toString()));

            libroRepository.save(libro);
            autorRepository.save(autor);

        } catch (Exception e) {
            System.out.println("no se encontro ese libro");
        }

    }

    // Trae los libros guardados en la BD
    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                    l.getAutor(),
                    l.getLenguaje(),
                    l.getDescargas().toString()));
        });
    }

    // Trae todos los autores de los libros consultados en la BD
    private void consultarAutores() {
        autores = autorRepository.findAll();
        autores.stream().forEach(a -> {
            System.out.println("""
                        Autor: %s
                        Año de nacimiento: %s
                        Año de defuncion: %s
                    """.formatted(a.getAutor(),
                    a.getNacimiento().toString(),
                    a.getDefuncion().toString()));
        });
    }

    // Trae a los autores apartir de cierto año
    public void consultarAutoresPorAno() {
        System.out.println("Ingresa el año a partir del cual buscar:");
        var anoBusqueda = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = autorRepository.autorPorFecha(anoBusqueda);
        autores.forEach(a -> {
            System.out.println("""
                    Nombre: %s
                    Fecha de nacimiento: %s
                    Fecha de defuncion: %s
                    """.formatted(a.getAutor(), a.getNacimiento().toString(), a.getDefuncion().toString()));
        });
    }

    private void consultarLibrosLenguaje() {
        System.out.println("""
                ****************************************************************
                    Selcciona el lenguaje de los libros que deseas consultar
                ****************************************************************
                1 - En (Ingles)
                2 - Es (Español)
                """);

        try {

            var opcion2 = scanner.nextInt();
            scanner.nextLine();

            switch (opcion2) {
                case 1:
                    libros = libroRepository.findByLenguaje("en");
                    break;
                case 2:
                    libros = libroRepository.findByLenguaje("es");
                    break;

                default:
                    System.out.println("Ingresa una opcion valida");
            }

            libros.stream().forEach(l -> {
                System.out.println("""
                            Titulo: %s
                            Autor: %s
                            Lenguaje: %s
                            Descargas: %s
                        """.formatted(l.getTitulo(),
                        l.getAutor(),
                        l.getLenguaje(),
                        l.getDescargas().toString()));
            });

        } catch (Exception e) {
            System.out.println("Ingresa un valor valido");
        }
    }

    private void mostrarEstadisticasLibrosPorIdioma() {
        // Cargar todos los libros desde la base de datos si no están ya cargados
        if (libros == null || libros.isEmpty()) {
            libros = libroRepository.findAll();
        }

        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos para generar estadísticas.");
            return;
        }

        // Usar Streams para agrupar y contar libros por idioma
        Map<String, Long> estadisticasPorIdioma = libros.stream()
                .collect(Collectors.groupingBy(Libro::getLenguaje, Collectors.counting()));

        System.out.println("\n--- Estadísticas de Libros por Idioma ---");
        estadisticasPorIdioma.forEach((idioma, cantidad) -> {
            String nombreIdioma = obtenerNombreIdioma(idioma); // Método auxiliar para mostrar el nombre completo del
                                                               // idioma
            System.out.printf("Idioma: %s (%s) - Cantidad: %d%n", nombreIdioma, idioma, cantidad);
        });
        System.out.println("-----------------------------------------\n");
    }

    // Método auxiliar para obtener el nombre completo del idioma (opcional)
    private String obtenerNombreIdioma(String codigoIdioma) {
        return switch (codigoIdioma) {
            case "es" -> "Español";
            case "en" -> "Inglés";
            case "fr" -> "Francés";
            case "pt" -> "Portugués";
            case "de" -> "Alemán";
            case "it" -> "Italiano";
            // Agrega más casos según los idiomas que manejes en tu API
            default -> "Desconocido";
        };
    }

}