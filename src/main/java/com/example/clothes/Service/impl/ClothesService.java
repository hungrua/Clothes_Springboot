package com.example.clothes.Service.impl;

import com.example.clothes.DTO.RequestDTO.ClothesRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesResponse;
import com.example.clothes.DTO.ResponseDTO.TypeDetailsResponse;
import com.example.clothes.Entity.Clothes;
import com.example.clothes.Entity.TypeDetails;
import com.example.clothes.Exception.AppException;
import com.example.clothes.Exception.ErrorCode;
import com.example.clothes.Mapper.ClothesMapper;
import com.example.clothes.Repository.ClothesRepository;
import com.example.clothes.Repository.TypeDetailsRepository;
import com.example.clothes.Service.IClothesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClothesService implements IClothesService {

    public static final Logger log = LogManager.getLogger();
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private TypeDetailsRepository typeDetailsRepository;
    @Autowired
    private ClothesMapper clothesMapper;

    /**
     * get list of all clothes that is not deleted
     *
     * @return list of all clothes that is not deleted
     */
    @Override
    public List<ClothesResponse> getAllClothes() {
        List<Clothes> clothesList = clothesRepository.findByIsDeletedFalse();
        List<ClothesResponse> clothesResponseList = new ArrayList<>();
        for (Clothes clothes : clothesList) {
            clothesResponseList.add(clothesMapper.toClothesResponse(clothes));
        }
        return clothesResponseList;
    }

    /**
     * Get the clothes by its it
     *
     * @param id -- clothes id
     * @return clothes have the id
     */
    @Override
    public ClothesResponse findClothesById(Integer id) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        return clothesMapper.toClothesResponse(clothes);
    }

    /**
     * Create new clothes
     *
     * @param request -- new clothes information
     * @return new clothes
     */
    @Override
    public ClothesResponse createClothes(ClothesRequest request) {
        Clothes clothes = clothesMapper.toClothes(request);
        if (clothesRepository.existsByClothesCode(request.getClothesCode())) {
            log.error("Không thể tạo mới quần áo này do mã {} đã tồn tại trong hệ thống", request.getClothesCode());
            throw new AppException(ErrorCode.DUPLICATE_FIELD_CODE);
        }
        List<TypeDetails> typeDetailsOfClothes = typeDetailsRepository.findAllByIdIn(request.getTypeDetails());
        clothes.setTypeDetails(new HashSet<>(typeDetailsOfClothes));
        clothes.setDeleted(false);
        clothes.setCreatedAt(new Date());
        Clothes newClothes = clothesRepository.save(clothes);
        return clothesMapper.toClothesResponse(newClothes);
    }

    /**
     * Create new clothes
     *
     * @param id      -- id of clothes is updated
     * @param request -- clothes information needed to update
     * @return updated clothes
     */
    @Override
    public ClothesResponse updateClothes(Integer id, ClothesRequest request) {
        Clothes clothes = clothesRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothesMapper.updateClothes(clothes, request);
        List<TypeDetails> typeDetailsOfClothes = typeDetailsRepository.findAllByIdIn(request.getTypeDetails());
        clothes.setTypeDetails(new HashSet<>(typeDetailsOfClothes));
        clothes.setUpdatedAt(new Date());
        clothes = clothesRepository.save(clothes);
        return clothesMapper.toClothesResponse(clothes);
    }

    /**
     * @param id -- id of clothes needed to be deleted
     */
    @Override
    public void deleteClothes(Integer id) {
        Clothes clothes = clothesRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothes.getTypeDetails().clear();
        clothes.setDeleted(true);
        clothesRepository.save(clothes);
    }
}
