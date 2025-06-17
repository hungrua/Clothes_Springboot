package com.example.clothes.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SizeResponse {
    private Integer id;
    private String code;
    private ClothesTypeResponse clothesType;
}
