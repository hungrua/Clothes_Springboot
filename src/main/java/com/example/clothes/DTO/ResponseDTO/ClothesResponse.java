package com.example.clothes.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClothesResponse {
    private Integer id;
    private String clothesCode;
    private String clothesName;
    private String description;
    private Set<TypeDetailsResponse> typeDetails;
}
