package com.buscador.buscador.repository;

import com.buscador.buscador.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

    List<Pelicula> findByDirectorContainingIgnoreCase(String director);

    List<Pelicula> findByAnio(int anio);

    List<Pelicula> findByTituloContainingIgnoreCaseAndDirectorContainingIgnoreCaseAndAnio(
            String titulo, String director, int anio
    );
}
