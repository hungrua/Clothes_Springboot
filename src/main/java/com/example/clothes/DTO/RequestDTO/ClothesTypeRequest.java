package com.example.clothes.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClothesTypeRequest {
    @NotBlank(message = "EMPTY_FIELD")
    private String code;
    @NotBlank(message = "EMPTY_FIELD")
    private String name;
    private String description;
}
