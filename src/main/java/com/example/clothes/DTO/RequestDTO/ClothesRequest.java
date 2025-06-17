package com.example.clothes.DTO.RequestDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClothesRequest {
    @NotBlank(message = "EMPTY_FIELD")
    private String clothesCode;
    @NotBlank(message = "EMPTY_FIELD")
    private String clothesName;
    private String description;
    @NotBlank(message = "EMPTY_FIELD")
    private Set<Integer> typeDetails;
}
