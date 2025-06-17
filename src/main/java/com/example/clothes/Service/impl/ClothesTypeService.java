package com.example.clothes.Service.impl;

import com.example.clothes.DTO.RequestDTO.ClothesTypeRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesTypeResponse;
import com.example.clothes.Entity.ClothesType;
import com.example.clothes.Exception.AppException;
import com.example.clothes.Exception.ErrorCode;
import com.example.clothes.Mapper.ClothesTypeMapper;
import com.example.clothes.Repository.ClothesTypeRepository;
import com.example.clothes.Service.IClothesTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClothesTypeService implements IClothesTypeService {
    public static final Logger log = LogManager.getLogger();
    @Autowired
    private ClothesTypeRepository clothesTypeRepository;
    @Autowired
    private ClothesTypeMapper clothesTypeMapper;

    /**
     * get list of all clothes type that is not deleted
     *
     * @return list of all clothes type that is not deleted
     */
    @Override
    public List<ClothesTypeResponse> getAllClothesType() {
        List<ClothesType> clothesTypeList = clothesTypeRepository.findByIsDeletedFalse();
        List<ClothesTypeResponse> clothesTypeResponseList = new ArrayList<>();
        for (ClothesType clothesType : clothesTypeList) {
            clothesTypeResponseList.add(clothesTypeMapper.toClosClothesTypeResponse(clothesType));
        }
        return clothesTypeResponseList;
    }

    /**
     * Get the clothes type by its it
     *
     * @param id -- clothes type id
     * @return clothes type have the id
     */
    @Override
    public ClothesTypeResponse findClothesTypeById(Integer id) {
        ClothesType clothesType = clothesTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        return clothesTypeMapper.toClosClothesTypeResponse(clothesType);
    }

    /**
     * Create new clothes type
     *
     * @param request -- new clothes type information
     * @return new clothes type
     */
    @Override
    public ClothesTypeResponse createClothesType(ClothesTypeRequest request) {
        ClothesType clothesType = clothesTypeMapper.toClothesType(request);
        if (clothesTypeRepository.existsByCode(request.getCode())) {
            log.error("Không thể tạo mới thể loại này do mã {} đã tồn tại trong hệ thống", request.getCode());
            throw new AppException(ErrorCode.DUPLICATE_FIELD_CODE);
        }
        clothesType.setDeleted(false);
        clothesType.setCreatedAt(new Date());
        ClothesType newClothesType = clothesTypeRepository.save(clothesType);
        return clothesTypeMapper.toClosClothesTypeResponse(newClothesType);
    }

    /**
     * Create new clothes type
     *
     * @param id      -- id of clothes type is updated
     * @param request -- clothes type information needed to update
     * @return updated clothes type
     */
    @Override
    public ClothesTypeResponse updateClothesType(Integer id, ClothesTypeRequest request) {
        ClothesType clothesType = clothesTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothesTypeMapper.updateClothesType(clothesType, request);
        clothesType.setUpdatedAt(new Date());
        clothesType = clothesTypeRepository.save(clothesType);
        return clothesTypeMapper.toClosClothesTypeResponse(clothesType);
    }

    /**
     * @param id -- id of clothes type needed to be deleted
     */
    @Override
    public void deleteClothesType(Integer id) {
        ClothesType clothesType = clothesTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothesType.setDeleted(true);
        clothesTypeRepository.save(clothesType);
    }
}
