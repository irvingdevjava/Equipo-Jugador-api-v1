package com.relaciones.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relaciones.demo.Servicios.EquipoService;
import com.relaciones.demo.modelos.Equipo;


@RestController
@RequestMapping("/api/v1")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @PostMapping("/equipo")
public ResponseEntity<?> addEquipo(@RequestParam String nombreEquipo, @RequestParam Long id) {
   return equipoService.addEquipo(nombreEquipo, id);
}

@GetMapping("/equipos")
public List<Equipo> getEquipos() {
    return equipoService.getEquipos();
}


}
