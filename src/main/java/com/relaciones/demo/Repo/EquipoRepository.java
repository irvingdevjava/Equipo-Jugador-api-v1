package com.relaciones.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.relaciones.demo.modelos.Equipo;
import com.relaciones.demo.modelos.Jugador;

public interface EquipoRepository extends JpaRepository<Equipo, Long>{
    Optional<Equipo> findByJugador(Jugador jugador);
}
