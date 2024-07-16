package com.relaciones.demo.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.relaciones.demo.Repo.EquipoRepository;
import com.relaciones.demo.Repo.JugadorRepository;
import com.relaciones.demo.modelos.Equipo;
import com.relaciones.demo.modelos.Jugador;

import jakarta.transaction.Transactional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private JugadorRepository jugadorRepository;

    @Transactional
    public List<Equipo> getEquipos() {
    return equipoRepository.findAll();
}

@Transactional
public ResponseEntity<?> addEquipo(String nombreEquipo,  Long id) {
    try {
        Optional<Jugador> optionalJugador = jugadorRepository.findById(id);

        if (optionalJugador.isPresent()) {
            Jugador jugador = optionalJugador.get();

            Optional<Equipo> equipoExistente = equipoRepository.findByJugador(jugador);

            if (equipoExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El jugador ya está asociado a otro equipo.");
            }

            
            Equipo equipo = Equipo.builder().nombre(nombreEquipo).jugador(jugador).build();
            Equipo equipoGuardado = equipoRepository.save(equipo);

            return ResponseEntity.status(HttpStatus.OK).body(equipoGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jugador no encontrado");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

}
