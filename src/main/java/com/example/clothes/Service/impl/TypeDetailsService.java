package com.example.clothes.Service.impl;

import com.example.clothes.DTO.RequestDTO.TypeDetailsRequest;
import com.example.clothes.DTO.ResponseDTO.TypeDetailsResponse;
import com.example.clothes.Entity.ClothesType;
import com.example.clothes.Entity.TypeDetails;
import com.example.clothes.Exception.AppException;
import com.example.clothes.Exception.ErrorCode;
import com.example.clothes.Mapper.TypeDetailsMapper;
import com.example.clothes.Repository.ClothesTypeRepository;
import com.example.clothes.Repository.TypeDetailsRepository;
import com.example.clothes.Service.ITypeDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TypeDetailsService implements ITypeDetailsService {
    public static final Logger log = LogManager.getLogger();
    @Autowired
    private TypeDetailsRepository typeDetailsRepository;
    @Autowired
    private ClothesTypeRepository clothesTypeRepository;
    @Autowired
    private TypeDetailsMapper typeDetailsMapper;

    /**
     * Get list of all type details that are not deleted
     *
     * @return list of all type details that are not deleted
     */
    @Override
    public List<TypeDetailsResponse> getAllTypeDetails() {
        List<TypeDetails> typeDetailsList = typeDetailsRepository.findByIsDeletedFalse();
        return typeDetailsList.stream()
                .map((typeDetails) -> typeDetailsMapper.toTypeDetailsResponse(typeDetails))
                .toList();
    }

    /**
     * Get the type details by its id
     *
     * @param id -- type details id
     * @return type details have the id
     */
    @Override
    public TypeDetailsResponse findTypeDetailsById(Integer id) {
        TypeDetails typeDetails = typeDetailsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        return typeDetailsMapper.toTypeDetailsResponse(typeDetails);
    }

    /**
     * Create new type details
     *
     * @param request -- create information
     * @return a new type details have been created
     */
    @Override
    public TypeDetailsResponse createTypeDetails(TypeDetailsRequest request) {
        TypeDetails typeDetails = typeDetailsMapper.toTypeDetails(request);
        if (typeDetailsRepository.existsByCode(request.getCode())) {
            log.error("Không thể tạo mới loại quần áo này do mã {} đã tồn tại trong hệ thống", request.getCode());
            throw new AppException(ErrorCode.DUPLICATE_FIELD_CODE);
        }
        ClothesType clothesType = clothesTypeRepository.findById(request.getType_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        log.debug("Tạo mới: Thể loại lấy từ DB khi tạo loại quần áo có mã là {} ", clothesType.getCode());
        typeDetails.setClothesType(clothesType);
        typeDetails.setDeleted(false);
        typeDetails.setCreatedAt(new Date());
        TypeDetails newTypeDetails = typeDetailsRepository.save(typeDetails);
        log.info("Tạo mới loại quần áo với mã {} thành công", newTypeDetails.getCode());
        return typeDetailsMapper.toTypeDetailsResponse(newTypeDetails);
    }

    /**
     * Update type details
     *
     * @param id      -- id of type details
     * @param request -- update information
     * @return updated type details
     */
    @Override
    public TypeDetailsResponse updateTypeDetails(Integer id, TypeDetailsRequest request) {
        TypeDetails typeDetails = typeDetailsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        // Attach Clothes Type to Type Details if type_id not null
        ClothesType clothesType = clothesTypeRepository.findById(request.getType_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        log.debug("Cập nhật: Thể loại lấy từ DB khi tạo loại quần áo có mã là {} ", clothesType.getCode());
        typeDetails.setClothesType(clothesType);
        // Update Clothes Type
        typeDetailsMapper.updateTypeDetails(typeDetails, request);
        typeDetails.setUpdatedAt(new Date());
        typeDetails = typeDetailsRepository.save(typeDetails);
        log.info("Cập nhật loại quần áo với mã {} thành công", typeDetails.getCode());
        return typeDetailsMapper.toTypeDetailsResponse(typeDetails);
    }

    /**
     * Delete soft type details
     *
     * @param id -- id of type details
     */
    @Override
    public void deleteTypeDetails(Integer id) {
        TypeDetails typeDetails = typeDetailsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        typeDetails.setDeleted(true);
        typeDetailsRepository.save(typeDetails);
        log.info("Xóa loại quần áo với mã {} thành công", typeDetails.getCode());
    }
}
