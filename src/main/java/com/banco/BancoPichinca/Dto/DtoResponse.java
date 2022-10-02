/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco.BancoPichinca.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author srcisnerosv
 */
@Data

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class DtoResponse {

    private Long codigoCreacion;
    private Integer codigoRespuesta;
    private String mensaje;
    private String causaExitoError;

    public DtoResponse() {
    }

    public DtoResponse(Long codigoCreacion, Integer codigoRespuesta, String mensaje, String causaExitoError) {
        this.codigoCreacion = codigoCreacion;
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.causaExitoError = causaExitoError;
    }

    public DtoResponse(Integer codigoRespuesta, String mensaje, String causaExitoError) {
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.causaExitoError = causaExitoError;
    }

    public DtoResponse(Integer codigoRespuesta, String mensaje) {
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
    }
}
