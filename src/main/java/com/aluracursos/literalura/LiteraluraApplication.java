package com.aluracursos.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluracursos.literalura.principal.Principal;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final LibroRepository libroRepository;
	private final AutorRepository autorRepository;

	// Inyección de dependencias a través del constructor
	public LiteraluraApplication(LibroRepository libroRepository, AutorRepository autorRepository) {
		this.libroRepository = libroRepository;
		this.autorRepository = autorRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Llamada a la lógica de negocio
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
		// // Aquí podrías agregar más lógica si es necesario
		// Por ejemplo, cargar datos iniciales o realizar alguna tarea al iniciar la
		// aplicación
		// principal.cargarDatosIniciales();
		// principal.realizarTareasAlInicio();
	}
}
