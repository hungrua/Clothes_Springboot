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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SizeService implements ISizeService {

    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ClothesTypeRepository clothesTypeRepository;
    @Autowired
    private SizeMapper sizeMapper;


    @Override
    public List<SizeResponse> getAllSize() {
        List<Size> sizeList = sizeRepository.findByIsDeletedFalse();
        return sizeList.stream()
                .map((size) -> sizeMapper.toSizeResponse(size))
                .toList();
    }

    @Override
    public SizeResponse findSizeById(Integer id) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        return sizeMapper.toSizeResponse(size);
    }

    @Override
    public SizeResponse createSize(SizeRequest request) {
        Size size = sizeMapper.toSize(request);
        ClothesType clothesType = clothesTypeRepository.findById(request.getType_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        size.setClothesType(clothesType);
        size.setDeleted(false);
        size.setCreatedAt(new Date());
        Size newSize = sizeRepository.save(size);
        return sizeMapper.toSizeResponse(newSize);
    }

    @Override
    public SizeResponse updateSize(Integer id, SizeRequest request) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        // Attach Clothes Type to Type Details if type_id not null
        if (request.getType_id() != null) {
            ClothesType clothesType = clothesTypeRepository.findById(request.getType_id()).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
            size.setClothesType(clothesType);
        }
        sizeMapper.updateSize(size, request);
        size.setUpdatedAt(new Date());
        size = sizeRepository.save(size);
        return sizeMapper.toSizeResponse(size);
    }

    @Override
    public void deleteSize(Integer id) {
        Size size = sizeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PARAMETER_NOT_FOUND));
        size.setDeleted(true);
        sizeRepository.save(size);
    }
}
