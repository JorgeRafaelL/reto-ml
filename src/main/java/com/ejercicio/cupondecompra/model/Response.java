package com.ejercicio.cupondecompra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static com.ejercicio.cupondecompra.util.Constant.ITEM_IDS_JSON_PROPERTY_INDEX;
import static com.ejercicio.cupondecompra.util.Constant.ITEM_IDS_JSON_PROPERTY_VALUE;

@Getter
@Builder
public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = -5680869187051124472L;

    @JsonProperty(value = ITEM_IDS_JSON_PROPERTY_VALUE, index = ITEM_IDS_JSON_PROPERTY_INDEX)
    private List<String> itemIds;
    private Float total;

}
