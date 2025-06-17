package com.example.clothes.Service;


import com.example.clothes.DTO.RequestDTO.SizeRequest;
import com.example.clothes.DTO.ResponseDTO.SizeResponse;

import java.util.List;

public interface ISizeService {
    List<SizeResponse> getAllSize();

    SizeResponse findSizeById(Integer id);

    SizeResponse createSize(SizeRequest request);

    SizeResponse updateSize(Integer id, SizeRequest request);

    void deleteSize(Integer id);
}
