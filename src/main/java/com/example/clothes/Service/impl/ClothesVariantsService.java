package com.example.clothes.Service.impl;

import com.example.clothes.DTO.RequestDTO.ClothesVariantsRequest;
import com.example.clothes.DTO.ResponseDTO.ClothesVariantsResponse;
import com.example.clothes.Entity.Clothes;
import com.example.clothes.Entity.ClothesVariants;
import com.example.clothes.Exception.AppException;
import com.example.clothes.Exception.ErrorCode;
import com.example.clothes.Mapper.ClothesVariantsMapper;
import com.example.clothes.Repository.ClothesRepository;
import com.example.clothes.Repository.ClothesVariantsRepository;
import com.example.clothes.Repository.ColorRepository;
import com.example.clothes.Repository.SizeRepository;
import com.example.clothes.Service.IClothesVariantsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClothesVariantsService implements IClothesVariantsService {
    public static final Logger log = LogManager.getLogger();
    @Autowired
    private ClothesVariantsRepository clothesVariantsRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ClothesVariantsMapper clothesVariantsMapper;

    /**
     * get list of all clothesVariants that is not deleted
     *
     * @return list of all clothesVariants that is not deleted
     */
    @Override
    public List<ClothesVariantsResponse> getAllClothesVariants() {
        List<ClothesVariants> clothesVariantsList = clothesVariantsRepository.findByIsDeletedFalse();
        List<ClothesVariantsResponse> clothesVariantsResponseList = new ArrayList<>();
        for (ClothesVariants clothesVariants : clothesVariantsList) {
            clothesVariantsResponseList.add(clothesVariantsMapper.toClothesVariantsResponse(clothesVariants));
        }
        return clothesVariantsResponseList;
    }

    /**
     * Get the clothesVariants by its it
     *
     * @param id -- clothesVariants id
     * @return clothesVariants have the id
     */
    @Override
    public ClothesVariantsResponse findClothesVariantsById(Integer id) {
        ClothesVariants clothesVariants = clothesVariantsRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        return clothesVariantsMapper.toClothesVariantsResponse(clothesVariants);
    }

    /**
     * Create new clothesVariants
     *
     * @param request -- new clothesVariants information
     * @return new clothesVariants
     */
    @Override
    public ClothesVariantsResponse createClothesVariants(ClothesVariantsRequest request) {
        ClothesVariants clothesVariants = clothesVariantsMapper.toClothesVariants(request);
        Clothes clothes = clothesRepository.findById(request.getClothes_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothesVariants.setClothes(clothes);
        log.debug("Quần áo lấy từ DB khi tạo biến thể quần áo có mã là {} ", clothes.getClothesCode());
        clothesVariants.setColor(colorRepository.findById(request.getColor_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setSize(sizeRepository.findById(request.getSize_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setDeleted(false);
        clothesVariants.setCreatedAt(new Date());
        ClothesVariants newClothesVariants = clothesVariantsRepository.save(clothesVariants);
        return clothesVariantsMapper.toClothesVariantsResponse(newClothesVariants);
    }

    /**
     * Create new clothesVariants
     *
     * @param id      -- id of clothesVariants is updated
     * @param request -- clothesVariants information needed to update
     * @return updated clothesVariants
     */
    @Override
    public ClothesVariantsResponse updateClothesVariants(Integer id, ClothesVariantsRequest request) {
        ClothesVariants clothesVariants = clothesVariantsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothesVariantsMapper.updateClothesVariants(clothesVariants, request);
        clothesVariants.setClothes(clothesRepository.findById(request.getClothes_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setColor(colorRepository.findById(request.getColor_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setSize(sizeRepository.findById(request.getSize_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setUpdatedAt(new Date());
        clothesVariants = clothesVariantsRepository.save(clothesVariants);
        return clothesVariantsMapper.toClothesVariantsResponse(clothesVariants);
    }

    /**
     * @param id -- id of clothesVariants needed to be deleted
     */
    @Override
    public void deleteClothesVariants(Integer id) {
        ClothesVariants clothesVariants = clothesVariantsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        clothesVariants.setDeleted(true);
        clothesVariantsRepository.save(clothesVariants);
    }
}
