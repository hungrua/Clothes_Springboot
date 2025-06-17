package com.example.clothes.Mapper;

import com.example.clothes.DTO.RequestDTO.ClothesTypeRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesTypeResponse;
import com.example.clothes.Entity.ClothesType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClothesTypeMapper {
    /**
     * Transform ClothesType  request DTO to entity
     *
     * @param request -- Clothes Type information
     * @return ClothesType in Entity
     */
    ClothesType toClothesType(ClothesTypeRequest request);

    /**
     * Transform ClothesType entity to response DTO
     *
     * @param clothesType -- Clothes Type Entity
     * @return ClothesType in Entity
     */
    ClothesTypeResponse toClosClothesTypeResponse(ClothesType clothesType);

    void updateClothesType(@MappingTarget ClothesType clothesType, ClothesTypeRequest request);
}
