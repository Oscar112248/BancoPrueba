/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.banco.BancoPichinca.Servicio;

import com.banco.BancoPichinca.Dto.DtoEstadoCuenta;
import com.banco.BancoPichinca.Entidades.Cuenta;
import com.banco.BancoPichinca.Entidades.Movimientos;
import com.banco.BancoPichinca.Repository.CuentaRepository;
import com.banco.BancoPichinca.Repository.MovimientosRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author oscar
 */
@Service
public class ServicioCuenta {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientosRepository movimientosRepository;

    @Transactional
    public boolean guardarCuentaCliente(Cuenta cuenta) {
        boolean respuesta = false;
        Movimientos movimientos = new Movimientos();
        Date fechaMovimiento = new Date();

        try {

            var cuentaCreada = cuentaRepository.save(cuenta);
            if (cuentaCreada != null) {
                movimientos.setFechaMovimiento(fechaMovimiento);
                movimientos.setIdCuenta(cuentaCreada);
                movimientos.setSaldo(cuenta.getSaldoInicial());
                movimientos.setTipoMovimiento("Deposito");
                movimientos.setValor(cuenta.getSaldoInicial());

                movimientosRepository.save(movimientos);
            }
            respuesta = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public List<DtoEstadoCuenta> reporte(String fechaInicio, String fechaFin, Long clienteId) {

        List<DtoEstadoCuenta> dtoEstadoCuentas = new ArrayList<>();
        try {

            String inicio = fechaInicio;
            String fin = fechaFin;
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInicio = formatter1.parse(inicio);
            Date dateFin = formatter1.parse(fin);
            var data = "";
            List<String> datos = cuentaRepository.obtenerEstadoCuenta(clienteId, inicio, fin);

            for (int x = 0; x < datos.size(); x++) {
                data = datos.get(x);
                String[] split = data.split(",");
                List<String> listaDatos = new ArrayList<>();

                for (int j = 0; j < split.length; j++) {

                    listaDatos.add(split[j]);
                }
                DtoEstadoCuenta dtoEstadoCuenta = new DtoEstadoCuenta();
                dtoEstadoCuenta.setFecha(listaDatos.get(0).toString());
                dtoEstadoCuenta.setNombre(listaDatos.get(1).toString());
                dtoEstadoCuenta.setNumeroCuenta(listaDatos.get(2).toString());
                dtoEstadoCuenta.setSaldo(new BigDecimal(listaDatos.get(3).toString()));
                dtoEstadoCuenta.setEstado(listaDatos.get(4).toString());
                dtoEstadoCuenta.setTipoMovimiento(listaDatos.get(5).toString());
                dtoEstadoCuenta.setValor(new BigDecimal(listaDatos.get(6).toString()));
                dtoEstadoCuentas.add(dtoEstadoCuenta);
            }

            return dtoEstadoCuentas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
