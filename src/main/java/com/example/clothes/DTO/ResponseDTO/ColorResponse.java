package com.example.clothes.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorResponse {
    private Integer id;
    private String colorCode;
    private String colorName;
    private String description;
}
