package com.example.clothes.Service.impl;

import com.example.clothes.DTO.RequestDTO.SizeRequest;
import com.example.clothes.DTO.ResponseDTO.SizeResponse;
import com.example.clothes.Entity.ClothesType;
import com.example.clothes.Entity.Size;
import com.example.clothes.Exception.AppException;
import com.example.clothes.Exception.ErrorCode;
import com.example.clothes.Mapper.SizeMapper;
import com.example.clothes.Repository.ClothesTypeRepository;
import com.example.clothes.Repository.SizeRepository;
import com.example.clothes.Service.ISizeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SizeService implements ISizeService {
    public static final Logger log = LogManager.getLogger();
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ClothesTypeRepository clothesTypeRepository;
    @Autowired
    private SizeMapper sizeMapper;

    /**
     * Get list of all sizes that are not deleted
     *
     * @return List of all sizes that are not deleted
     */
    @Override
    public List<SizeResponse> getAllSize() {
        List<Size> sizeList = sizeRepository.findByIsDeletedFalse();
        return sizeList.stream()
                .map((size) -> sizeMapper.toSizeResponse(size))
                .toList();
    }

    /**
     * Get the size by its id
     *
     * @param id -- size id
     * @return SizeResponse object with the given id
     */
    @Override
    public SizeResponse findSizeById(Integer id) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> {
            log.error("Không tìm thấy kích thước với id {} trong hệ thống", id);
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        return sizeMapper.toSizeResponse(size);
    }

    /**
     * Create a new size
     *
     * @param request -- SizeRequest object containing the size information
     * @return SizeResponse object of the newly created size
     */
    @Override
    public SizeResponse createSize(SizeRequest request) {
        Size size = sizeMapper.toSize(request);
        ClothesType clothesType = clothesTypeRepository.findById(request.getType_id()).orElseThrow(() -> {
            log.error("Không tìm thấy thể loại quần áo với id {} trong hệ thống", request.getType_id());
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        size.setClothesType(clothesType);
        size.setDeleted(false);
        size.setCreatedAt(new Date());
        Size newSize = sizeRepository.save(size);
        log.debug("Kích thước mới được tạo: {}", newSize);
        log.info("Tạo mới kích với id {} thước thành công", newSize.getId());
        return sizeMapper.toSizeResponse(newSize);
    }

    /**
     * Update an existing size
     *
     * @param id      -- ID of the size to update
     * @param request -- SizeRequest object containing the updated size information
     * @return SizeResponse object of the updated size
     */
    @Override
    public SizeResponse updateSize(Integer id, SizeRequest request) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> {
            log.error("Không tìm thấy kích thước với id {} trong hệ thống khi cập nhật", id);
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        // Attach Clothes Type to Type Details if type_id not null
        if (request.getType_id() != null) {
            ClothesType clothesType = clothesTypeRepository.findById(request.getType_id()).orElseThrow(() -> {
                log.error("Không tìm thấy thể loại quần áo với id {} " +
                        "trong hệ thống khi cập nhật kích thước", request.getType_id());
                return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
            });
            size.setClothesType(clothesType);
        }
        sizeMapper.updateSize(size, request);
        size.setUpdatedAt(new Date());
        size = sizeRepository.save(size);
        log.debug("Kích thước sau khi cập nhật: {}", size);
        log.info("Cập nhật kích thước với id {} thành công", size.getId());
        return sizeMapper.toSizeResponse(size);
    }

    /*
     * Soft delete a size by setting its deleted flag to true
     *
     * @param id -- ID of the size to delete
     */
    @Override
    public void deleteSize(Integer id) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        size.setDeleted(true);
        sizeRepository.save(size);
        log.info("Xóa kích thước với id {} thành công", id);
    }
}
