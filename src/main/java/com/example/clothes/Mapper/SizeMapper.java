package com.example.clothes.Mapper;

import com.example.clothes.DTO.RequestDTO.SizeRequest;
import com.example.clothes.DTO.ResponseDTO.SizeResponse;
import com.example.clothes.Entity.Size;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ClothesTypeMapper.class})
public interface SizeMapper {

    /**
     * Transform Size request DTO to entity
     *
     * @param request -- Size information
     * @return Size in Entity
     */
    Size toSize(SizeRequest request);

    /**
     * Transform Size entity to response DTO
     *
     * @param size -- Size Entity
     * @return Size in Entity
     */
    SizeResponse toSizeResponse(Size size);

    void updateSize(@MappingTarget Size size, SizeRequest request);
}
