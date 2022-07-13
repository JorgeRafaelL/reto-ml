package com.ejercicio.cupondecompra.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

import static com.ejercicio.cupondecompra.util.Constant.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(ITEMS_DOCUMENT)
@JsonInclude(Include.NON_NULL)
public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 8505734863592320044L;

    @Id
    private String mongoId;
    @JsonProperty(value = ID_JSON_PROPERTY_VALUE, index = ID_JSON_PROPERTY_INDEX)
    private String itemId;
    private String title;
    private Float price;
    @JsonProperty(SITE_ID_JSON_PROPERTY_VALUE)
    private String siteId;
    private Integer quantity;
}
