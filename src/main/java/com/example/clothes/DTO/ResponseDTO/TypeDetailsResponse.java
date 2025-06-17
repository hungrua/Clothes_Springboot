package com.example.clothes.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeDetailsResponse {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private ClothesTypeResponse clothesType;
}
