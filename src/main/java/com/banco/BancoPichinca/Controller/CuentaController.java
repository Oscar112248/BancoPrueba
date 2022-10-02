/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.banco.BancoPichinca.Controller;

import com.banco.BancoPichinca.Dto.DtoResponse;
import com.banco.BancoPichinca.Entidades.Cliente;
import com.banco.BancoPichinca.Entidades.Cuenta;
import com.banco.BancoPichinca.Entidades.Persona;
import com.banco.BancoPichinca.Servicio.ServicioCuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author oscar
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/cuentas")
public class CuentaController {
    
    @Autowired
    private ServicioCuenta servicioCuenta;
    
     @PostMapping("/crearCuentaCliente")
    public ResponseEntity<?> crearCuentaCliente(@RequestBody Cuenta  cuenta) {

        try {
            if (servicioCuenta.guardarCuentaCliente(cuenta)) {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.CREATED.value(), "Datos Grabados Correctamente"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(), "Error al grabar datos"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(),
                    "Error al grabar datos", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
   
    
     @GetMapping("/reporte/{cliente}/{fechaInicio}/{fechaFin}")
    public ResponseEntity<?> reporte(@PathVariable("cliente") Long cliente,
            @PathVariable("fechaInicio") String fechaInicio,
            @PathVariable("fechaFin") String fechaFin) {
        try {

            return ResponseEntity.ok(servicioCuenta.reporte(fechaInicio, fechaFin, cliente));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error al consultar", e);

        }
    }

    
}
