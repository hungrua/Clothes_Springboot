package com.example.clothes.Mapper;

import com.example.clothes.DTO.RequestDTO.ClothesVariantsRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesVariantsResponse;
import com.example.clothes.Entity.ClothesVariants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TypeDetailsMapper.class})
public interface ClothesVariantsMapper {
    /**
     * Transform ClothesVariants  request DTO to entity
     *
     * @param request -- ClothesVariants information
     * @return ClothesVariants in Entity
     */
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "clothes", ignore = true)
    ClothesVariants toClothesVariants(ClothesVariantsRequest request);

    /**
     * Transform ClothesVariants entity to response DTO
     *
     * @param clothes -- ClothesVariants Entity
     * @return ClothesVariants in Entity
     */
    ClothesVariantsResponse toClothesVariantsResponse(ClothesVariants clothes);

    @Mapping(target = "color", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "clothes", ignore = true)
    void updateClothesVariants(@MappingTarget ClothesVariants clothes, ClothesVariantsRequest request);
}
