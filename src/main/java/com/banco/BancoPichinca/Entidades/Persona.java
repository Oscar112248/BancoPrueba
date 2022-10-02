/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.BancoPichinca.Entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author oscar
 */
@Entity
@Table(name = "Persona", catalog = "Banco", schema = "banco",
         uniqueConstraints = {
            @UniqueConstraint(name = "Banco_01", columnNames = {"identificacion"})})
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {



    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "Persona_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personaId;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String direccion;
    @Basic(optional = false)
    @Column(nullable = false)
    private int edad;
    @Basic(optional = false)
    @Column(nullable = false)
    private Character genero;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String identificacion;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String telefono;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
//    private Cliente cliente;


    public Persona() {

    }

    public Persona(Long personaId, String direccion, int edad, Character genero, String identificacion, String nombre, String telefono) {
        this.personaId = personaId;
        this.direccion = direccion;
        this.edad = edad;
        this.genero = genero;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "personaId=" + personaId + ", direccion=" + direccion + ", edad=" + edad + ", genero=" + genero + ", identificacion=" + identificacion + ", nombre=" + nombre + ", telefono=" + telefono + '}';
    }

   

    
    
}
