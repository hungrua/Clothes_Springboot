package com.example.clothes.Mapper;

import com.example.clothes.DTO.RequestDTO.TypeDetailsRequest;
import com.example.clothes.DTO.ResponseDTO.TypeDetailsResponse;
import com.example.clothes.Entity.TypeDetails;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ClothesTypeMapper.class})
public interface TypeDetailsMapper {

    /**
     * Transform Type Details  request DTO to entity
     *
     * @param request -- Type Details information
     * @return Type Details in Entity
     */
    TypeDetails toTypeDetails(TypeDetailsRequest request);

    /**
     * Transform Type Details entity to response DTO
     *
     * @param typeDetails -- Type Details Entity
     * @return Type Details in Entity
     */
    TypeDetailsResponse toTypeDetailsResponse(TypeDetails typeDetails);

    void updateTypeDetails(@MappingTarget TypeDetails typeDetails, TypeDetailsRequest request);
}
