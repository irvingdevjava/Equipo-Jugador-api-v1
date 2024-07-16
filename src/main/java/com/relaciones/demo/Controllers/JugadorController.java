package com.relaciones.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relaciones.demo.Servicios.JugadorService;
import com.relaciones.demo.modelos.Jugador;



@RestController
@RequestMapping("/api/v1")
public class JugadorController {

    @Autowired
    private JugadorService jugadorf;

    @PostMapping("/jugador")
    public Jugador addJugador(@RequestParam String nombre) {
        return jugadorf.addJugador(nombre);
        
    }

   @DeleteMapping("/jugador/{id}")
    public ResponseEntity<?> removeJugador(@PathVariable Long id) {
       return jugadorf.removeJugador(id);
    }


     
    @GetMapping("/jugadores")
public ResponseEntity<?> jugadores() {
    return jugadorf.jugadores();
}

    @GetMapping("/jugador/{id}")
    public ResponseEntity<?> getJugador(@PathVariable Long id) {
        return jugadorf.getJugador(id);
    }
    
    
}
