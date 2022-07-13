package com.ejercicio.cupondecompra.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class ResponseException implements Serializable {

    @Serial
    private static final long serialVersionUID = -9147129475218122607L;

    private Integer code;
    private String status;
    private String message;
    private Object detail;
}
