package com.example.clothes.Mapper;

import com.example.clothes.DTO.RequestDTO.ClothesRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesResponse;
import com.example.clothes.Entity.Clothes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TypeDetailsMapper.class})
public interface ClothesMapper {
    /**
     * Transform Clothes  request DTO to entity
     *
     * @param request -- Clothes information
     * @return Clothes in Entity
     */
    @Mapping(target = "typeDetails", ignore = true)
    Clothes toClothes(ClothesRequest request);

    /**
     * Transform Clothes entity to response DTO
     *
     * @param clothes -- Clothes Entity
     * @return Clothes in Entity
     */
    ClothesResponse toClothesResponse(Clothes clothes);

    @Mapping(target = "typeDetails", ignore = true)
    void updateClothes(@MappingTarget Clothes clothes, ClothesRequest request);
}
