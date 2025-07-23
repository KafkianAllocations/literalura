package com.aluracursos.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aluracursos.literalura.models.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.nacimiento >= :anoBusqueda ORDER BY a.nacimiento ASC ")
    List<Autor> autorPorFecha(int anoBusqueda);

}
