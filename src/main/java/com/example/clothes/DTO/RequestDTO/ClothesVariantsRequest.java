package com.example.clothes.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClothesVariantsRequest {
    @NotNull(message = "EMPTY_FIELD")
    private Integer clothes_id;
    @NotNull(message = "EMPTY_FIELD")
    private Integer size_id;
    @NotNull(message = "EMPTY_FIELD")
    private Integer color_id;
    private Integer quantity;
    private Integer price;
}
