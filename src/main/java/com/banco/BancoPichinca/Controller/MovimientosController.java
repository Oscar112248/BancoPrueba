/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.banco.BancoPichinca.Controller;

import com.banco.BancoPichinca.Dto.DtoResponse;
import com.banco.BancoPichinca.Entidades.Cuenta;
import com.banco.BancoPichinca.Entidades.Persona;
import com.banco.BancoPichinca.Servicio.ServicioMovimientos;
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
@RequestMapping("/api/movimientos")
public class MovimientosController {

    @Autowired
    private ServicioMovimientos servicioMovimientos;

    @PostMapping("/realizarMovimiento/{tipoMovimiento}/{valorMovimiento}/{numeroCuenta}")
    public ResponseEntity<?> realizarMovimiento(@PathVariable("tipoMovimiento") String tipoMovimiento,
            @PathVariable("valorMovimiento") double valorMovimiento,
            @PathVariable("numeroCuenta") String numeroCuenta) {

        try {
            var respuesta = servicioMovimientos.realizarMovimiento(tipoMovimiento.toUpperCase(), valorMovimiento, numeroCuenta);
            if (respuesta.equals("OK")) {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.CREATED.value(), "Acción Realizada Correctamente"), HttpStatus.CREATED);
            } else if (respuesta.equals("-1")) {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(), "Saldo No Disponible"), HttpStatus.BAD_REQUEST);
            }
            if (respuesta.equals("-2")) {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(), "Cupo diario Excedido"), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(), "Existe un error en la transacción"), HttpStatus.BAD_REQUEST);

            }

        } catch (Exception e) {
            return new ResponseEntity<DtoResponse>(new DtoResponse(HttpStatus.BAD_REQUEST.value(),
                    "Existe un error en la transacción", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

   
}
