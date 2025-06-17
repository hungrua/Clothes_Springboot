package com.example.clothes.Service;

import com.example.clothes.DTO.RequestDTO.ClothesRequest;
import com.example.clothes.DTO.RequestDTO.ClothesVariantsRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesResponse;
import com.example.clothes.DTO.ResponseDTO.ClothesVariantsResponse;

import java.util.List;

public interface IClothesVariantsService {
    List<ClothesVariantsResponse> getAllClothesVariants();

    ClothesVariantsResponse findClothesVariantsById(Integer id);

    ClothesVariantsResponse createClothesVariants(ClothesVariantsRequest request);

    ClothesVariantsResponse updateClothesVariants(Integer id, ClothesVariantsRequest request);

    void deleteClothesVariants(Integer id);

}
