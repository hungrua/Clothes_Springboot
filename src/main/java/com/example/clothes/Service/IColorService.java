package com.example.clothes.Service;

import com.example.clothes.DTO.RequestDTO.ClothesTypeRequest;
import com.example.clothes.DTO.RequestDTO.ColorRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesTypeResponse;
import com.example.clothes.DTO.ResponseDTO.ColorResponse;

import java.util.List;

public interface IColorService {
    List<ColorResponse> getAllColor();

    ColorResponse findColorById(Integer id);

    ColorResponse createColor(ColorRequest request);

    ColorResponse updateColor(Integer id, ColorRequest request);

    void deleteColor(Integer id);

}
