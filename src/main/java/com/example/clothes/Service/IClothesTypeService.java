package com.example.clothes.Service;

import com.example.clothes.DTO.RequestDTO.ClothesTypeRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesTypeResponse;

import java.util.List;

public interface IClothesTypeService {
    List<ClothesTypeResponse> getAllClothesType();

    ClothesTypeResponse findClothesTypeById(Integer id);

    ClothesTypeResponse createClothesType(ClothesTypeRequest request);

    ClothesTypeResponse updateClothesType(Integer id, ClothesTypeRequest request);

    void deleteClothesType(Integer id);

}
