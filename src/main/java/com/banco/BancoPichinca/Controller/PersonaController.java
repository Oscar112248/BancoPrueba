/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.banco.BancoPichinca.Controller;

import com.banco.BancoPichinca.Dto.DtoPersona;
import com.banco.BancoPichinca.Entidades.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.banco.BancoPichinca.Dto.DtoResponse;
import com.banco.BancoPichinca.Entidades.Cliente;
import com.banco.BancoPichinca.Servicio.ServicioPersona;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author oscar
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/clientes")
public class PersonaController {
    
    @Autowired
    private ServicioPersona servicioPersona;

    @PostMapping("/guardarPersona")
    public ResponseEntity<?> guardarPersona(@RequestBody Cliente  cliente) {

        try {
            if (servicioPersona.guardarPersonaCliente(cliente)) {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.CREATED.value(), "Datos Grabados Correctamente"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(), "Error al grabar datos"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(),
                    "Error al grabar datos", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
    
    
     @GetMapping("/obtenerPersonas")
    public ResponseEntity<List<Persona>> obtenerPersona() {
        try {

            return ResponseEntity.ok(servicioPersona.obtenerPersonas());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error al consultar", e);

        }
    }
    
    
    @GetMapping("/obtenerPersonaDto/{ci}")
    public ResponseEntity<Set<DtoPersona>> obtenerPersonaDto(@PathVariable("ci") String ci) {
        try {

            return ResponseEntity.ok(servicioPersona.obtenerPersonasDto(ci));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error al consultar", e);

        }
    }

}
