/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.banco.BancoPichinca.Repository;

import com.banco.BancoPichinca.Dto.DtoEstadoCuenta;
import com.banco.BancoPichinca.Entidades.Cuenta;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author oscar
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query(value = "Select Saldo_Inicial from [Banco].[banco].cuenta where Numero_Cuenta = :numeroCuenta", nativeQuery = true)
    Double obtenerSaldo(@Param("numeroCuenta") String numeroCuenta);

    @Query("Select c from Cuenta c where c.numeroCuenta = :numeroCuenta")
    Cuenta obtenerCuenta(@Param("numeroCuenta") String numeroCuenta);

    @Query(value = "select mo.Fecha_Movimiento, p.nombre, cu.Numero_Cuenta, mo.saldo,cu.estado , mo.Tipo_Movimiento, mo.valor\n"
            + "from [Banco].[banco].[Cliente]  cli \n"
            + "inner join [Banco].[banco].Persona p\n"
            + "on p.Persona_Id = cli.Persona_Id\n"
            + "inner join [Banco].[banco].Cuenta cu\n"
            + "on cu.Id_Cliente = cli.Persona_Id\n"
            + "inner join [Banco].[banco].Movimientos mo\n"
            + "on mo.Id_Cuenta = cu.Id_Cuenta\n"
            + "where  p.Persona_Id = :clienteId and\n"
            + "Fecha_Movimiento >=  :fechaInicio and Fecha_Movimiento <= :fechaFin order by Fecha_Movimiento asc", nativeQuery = true)
    List<String> obtenerEstadoCuenta(@Param("clienteId") Long clienteId,
            @Param("fechaInicio") String fechaInicio,
            @Param("fechaFin") String fechaFin);

}
