package com.ejercicio.cupondecompra.model;

import com.ejercicio.cupondecompra.document.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiMLResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6779351955132433420L;

    private Integer code;
    @JsonProperty("body")
    private Item item;
}
