package com.example.clothes.Mapper;

import com.example.clothes.DTO.RequestDTO.ClothesTypeRequest;
import com.example.clothes.DTO.RequestDTO.ColorRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesTypeResponse;
import com.example.clothes.DTO.ResponseDTO.ColorResponse;
import com.example.clothes.Entity.ClothesType;
import com.example.clothes.Entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    /**
     * Transform Color  request DTO to entity
     *
     * @param request -- Color information
     * @return Color in Entity
     */
    Color toColor(ColorRequest request);

    /**
     * Transform Color entity to response DTO
     *
     * @param color -- Color Entity
     * @return Color in Entity
     */
    ColorResponse toColorResponse(Color color);

    void updateColor(@MappingTarget Color color, ColorRequest request);
}
