package com.ejercicio.cupondecompra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static com.ejercicio.cupondecompra.util.Constant.*;

@Getter
@Builder
public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 6327311979301512291L;

    @NotNull(message = NOT_NULL_ITEM_IDS_REQUEST_MESSAGE)
    @NotEmpty(message = NOT_EMPTY_ITEM_IDS_REQUEST_MESSAGE)
    @JsonProperty(ITEM_IDS_JSON_PROPERTY_VALUE)
    private List<String> itemIds;

    @NotNull(message = NOT_NULL_AMOUNT_REQUEST_MESSAGE)
    private Float amount;
}
