package com.buscador.buscador.controller;

import com.buscador.buscador.entity.Pelicula;
import com.buscador.buscador.service.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return peliculaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return peliculaService.guardar(pelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula peliculaActualizada) {
        return peliculaService.buscarPorId(id).map(p -> {
            p.setTitulo(peliculaActualizada.getTitulo());
            p.setDirector(peliculaActualizada.getDirector());
            p.setAnio(peliculaActualizada.getAnio());
            return ResponseEntity.ok(peliculaService.guardar(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (peliculaService.buscarPorId(id).isPresent()) {
            peliculaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public List<Pelicula> buscarPeliculas(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) Integer anio
    ) {
        return peliculaService.buscarPorFiltros(titulo, director, anio);
    }
}
