package com.buscador.buscador.service;

import com.buscador.buscador.entity.Pelicula;
import com.buscador.buscador.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> listar() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> buscarPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void eliminar(Long id) {
        peliculaRepository.deleteById(id);
    }

    public List<Pelicula> buscarPorFiltros(String titulo, String director, Integer anio) {
        if (titulo != null && director != null && anio != null) {
            return peliculaRepository.findByTituloContainingIgnoreCaseAndDirectorContainingIgnoreCaseAndAnio(titulo, director, anio);
        } else if (titulo != null) {
            return peliculaRepository.findByTituloContainingIgnoreCase(titulo);
        } else if (director != null) {
            return peliculaRepository.findByDirectorContainingIgnoreCase(director);
        } else if (anio != null) {
            return peliculaRepository.findByAnio(anio);
        } else {
            return peliculaRepository.findAll();
        }
    }
}
