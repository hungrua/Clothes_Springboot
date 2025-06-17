package com.example.clothes.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorRequest {
    @NotBlank(message = "EMPTY_FIELD")
    private String colorCode;
    @NotBlank(message = "EMPTY_FIELD")
    private String colorName;
    private String description;
}
