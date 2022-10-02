/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.banco.BancoPichinca.Repository;

import com.banco.BancoPichinca.Dto.DtoPersona;
import com.banco.BancoPichinca.Entidades.Persona;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author oscar
 */
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query("Select p from Persona p")
    List<Persona> obtenerPersonas();
    
    
    @Query("Select new  com.banco.BancoPichinca.Dto.DtoPersona(p.personaId, p.nombre,  p.identificacion)  from Persona p where p.identificacion = :ci")
    Set<DtoPersona> obtenerPersonaDto(@Param("ci")  String ci );

}
