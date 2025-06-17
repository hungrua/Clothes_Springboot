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
                .orElseThrow(() -> {
                    log.error("Không tìm thấy biến thể có id {} trong hệ thống", id);
                    return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
                });
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
        Clothes clothes = clothesRepository.findById(request.getClothes_id()).orElseThrow(() -> {
            log.error("Không tìm thấy loại quần áo có id {} trong hệ thống", request.getClothes_id());
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        clothesVariants.setClothes(clothes);
        log.debug("Quần áo lấy từ DB khi tạo biến thể quần áo có mã là {} ", clothes.getClothesCode());
        clothesVariants.setColor(colorRepository.findById(request.getColor_id()).orElseThrow(() -> {
            log.error("Không tìm thấy màu có id {} trong hệ thống", request.getColor_id());
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        }));
        clothesVariants.setSize(sizeRepository.findById(request.getSize_id()).orElseThrow(() -> {
            log.error("Không tìm thấy kích cỡ có id {} trong hệ thống", request.getSize_id());
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        }));
        clothesVariants.setDeleted(false);
        clothesVariants.setCreatedAt(new Date());
        ClothesVariants newClothesVariants = clothesVariantsRepository.save(clothesVariants);
        log.debug("Biến thể được tạo ra {}", newClothesVariants);
        log.info("Tạo biến thể thành công với id {}", newClothesVariants.getId());
        return clothesVariantsMapper.toClothesVariantsResponse(newClothesVariants);
    }

    /**
     * Update clothesVariants
     *
     * @param id      -- id of clothesVariants is updated
     * @param request -- clothesVariants information needed to update
     * @return updated clothesVariants
     */
    @Override
    public ClothesVariantsResponse updateClothesVariants(Integer id, ClothesVariantsRequest request) {
        ClothesVariants clothesVariants = clothesVariantsRepository.findById(id).orElseThrow(() -> {
            log.error("Không tìm thấy biến thể có id {} trong hệ thống để cập nhật", id);
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        clothesVariantsMapper.updateClothesVariants(clothesVariants, request);
        clothesVariants.setClothes(clothesRepository.findById(request.getClothes_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setColor(colorRepository.findById(request.getColor_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setSize(sizeRepository.findById(request.getSize_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND)));
        clothesVariants.setUpdatedAt(new Date());
        clothesVariants = clothesVariantsRepository.save(clothesVariants);
        log.debug("Biến thể được cập nhật {}", clothesVariants);
        log.info("Cập nhật biến thể với id {} thành công", clothesVariants.getId());
        return clothesVariantsMapper.toClothesVariantsResponse(clothesVariants);
    }

    /**
     * Delete clothesVariants
     *
     * @param id -- id of clothesVariants needed to be deleted
     */
    @Override
    public void deleteClothesVariants(Integer id) {
        ClothesVariants clothesVariants = clothesVariantsRepository.findById(id).orElseThrow(() -> {
            log.error("Không tìm thấy biến thể có id {} trong hệ thống để xóa", id);
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        clothesVariants.setDeleted(true);
        clothesVariantsRepository.save(clothesVariants);
        log.info("Xóa biến thể thành công");
    }
}
