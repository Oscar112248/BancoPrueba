/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.banco.BancoPichinca.Repository;

import com.banco.BancoPichinca.Entidades.Movimientos;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author oscar
 */
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

//    @Modifying
//    @Query("update Movimientos p set p.valor = :acreditacion, p.valor = :valor  where p.registroProfesionalId.registroProfesionalId = :registroProfesionalId ")
//    void actualizarSaldo(@Param("registroProfesionalId") Long registroProfesionalId, @Param("fechaAprobacion") Date fechaAprobacion,
//            @Param("novedad") String novedad, @Param("usuarioModificacion") String usuarioModificacion,
//            @Param("terminalModificacion") String terminalModificacion);
    @Query(value = "Select top 1  saldo from [Banco].[banco].Movimientos where Id_Cuenta = :cuentaId order by Fecha_Movimiento desc", nativeQuery = true)
    Double obtenerSaldo(@Param("cuentaId") Long cuentaId);

    @Query(value = "Select top 1 * from [Banco].[banco].Movimientos where Id_Cuenta = :numeroCuenta order by Fecha_Movimiento desc", nativeQuery = true)
    Movimientos obtenerCuentaAfectar(@Param("numeroCuenta") Long numeroCuenta);

    @Query(value = "Select sum(valor) as valorTope from [Banco].[banco].Movimientos where tipo_movimiento ='Retiro' and Id_Cuenta = :cuentaId \n"
            + "and fecha_Movimiento >=  dateadd(DAY, datediff(day, 0, getdate()),0)   and fecha_Movimiento <=  getdate() ", nativeQuery = true)
    Double obtenerValorTope(@Param("cuentaId") Long cuentaId);

}
