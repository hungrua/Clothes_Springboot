package com.example.clothes.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClothesVariantsResponse {
    private Integer id;
    private Integer quantity;
    private ClothesResponse clothes;
    private SizeResponse size;
    private ColorResponse color;
    private Integer price;
}
