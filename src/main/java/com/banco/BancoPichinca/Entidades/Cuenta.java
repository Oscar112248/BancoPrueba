/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.BancoPichinca.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oscar
 */
@Entity
@Table(catalog = "Banco", schema = "banco", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Numero_Cuenta"})})

public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id_Cuenta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;
    @Basic(optional = false)
    @Column(name = "Numero_Cuenta", nullable = false, length = 100)
    private String numeroCuenta;
    @Basic(optional = false)
    @Column(name = "Tipo_Cuenta", nullable = false, length = 50)
    private String tipoCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Saldo_Inicial", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoInicial;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String estado;

    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Persona_Id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference("idCliente")
    private Cliente idCliente;
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuenta", fetch = FetchType.LAZY)
    private Set<Movimientos> movimientosSet;

    public Cuenta() {
    }

    public Cuenta(Long idCuenta, String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, String estado, Cliente idCliente) {
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.idCliente = idCliente;
    }

  
    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Set<Movimientos> getMovimientosSet() {
        return movimientosSet;
    }

    public void setMovimientosSet(Set<Movimientos> movimientosSet) {
        this.movimientosSet = movimientosSet;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "idCuenta=" + idCuenta + ", numeroCuenta=" + numeroCuenta + ", tipoCuenta=" + tipoCuenta + ", saldoInicial=" + saldoInicial + ", estado=" + estado + ", idCliente=" + idCliente + ", movimientosSet=" + movimientosSet + '}';
    }

    
    
    

}
