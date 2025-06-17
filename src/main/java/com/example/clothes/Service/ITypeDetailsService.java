package com.example.clothes.Service;


import com.example.clothes.DTO.RequestDTO.TypeDetailsRequest;
import com.example.clothes.DTO.ResponseDTO.TypeDetailsResponse;

import java.util.List;

public interface ITypeDetailsService {
    List<TypeDetailsResponse> getAllTypeDetails();

    TypeDetailsResponse findTypeDetailsById(Integer id);

    TypeDetailsResponse createTypeDetails(TypeDetailsRequest request);

    TypeDetailsResponse updateTypeDetails(Integer id, TypeDetailsRequest request);

    void deleteTypeDetails(Integer id);
}
