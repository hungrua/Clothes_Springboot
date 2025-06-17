package com.example.clothes.Service;

import com.example.clothes.DTO.RequestDTO.ClothesRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesResponse;

import java.util.List;

public interface IClothesService {
    List<ClothesResponse> getAllClothes();

    ClothesResponse findClothesById(Integer id);

    ClothesResponse createClothes(ClothesRequest request);

    ClothesResponse updateClothes(Integer id, ClothesRequest request);

    void deleteClothes(Integer id);

}
