package com.relaciones.demo.Servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.relaciones.demo.Repo.JugadorRepository;
import com.relaciones.demo.modelos.Jugador;

import jakarta.transaction.Transactional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Transactional
    public Jugador addJugador(String nombre){
        Jugador newJugador= new Jugador();
        newJugador.setNombre(nombre);
        return jugadorRepository.save(newJugador);
    }

    @Transactional
    public ResponseEntity<?> removeJugador(Long id){
        Optional<Jugador> jugadorOptional = jugadorRepository.findById(id);
        
       try {
        if (jugadorOptional.isPresent()) {
            Jugador jugador = jugadorOptional.get();
            jugadorRepository.delete(jugador);
            return ResponseEntity.status(HttpStatus.OK).body("Jugador eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jugador no encontrado");
        }
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }

    @Transactional
    public ResponseEntity<?> jugadores(){
        Iterable<Jugador> jugadoresIterable = jugadorRepository.findAll();
    List<Jugador> jugadores=new ArrayList<>();
    jugadoresIterable.forEach(jugadores::add);
    
    try {
        if (jugadores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron jugadores");
        } else {
            return ResponseEntity.ok(jugadores);
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }


    @Transactional
    public ResponseEntity<?> getJugador(Long id) {
        try {
            Optional<Jugador> jugador=jugadorRepository.findById(id);
            
            if (jugador.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body( jugadorRepository.findById(id));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


}}
