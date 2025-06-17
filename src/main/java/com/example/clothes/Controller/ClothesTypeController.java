package com.example.clothes.Controller;

import com.example.clothes.DTO.RequestDTO.ClothesTypeRequest;
import com.example.clothes.DTO.ResponseDTO.ApiResponse;
import com.example.clothes.DTO.ResponseDTO.ClothesTypeResponse;
import com.example.clothes.Service.impl.ClothesTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clothes_types")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "https://clothes-fe-ten.vercel.app",
        "http://localhost:5173"
})
public class ClothesTypeController {

    private final ClothesTypeService clothesTypeService;

    /**
     * API get clothes type list
     *
     * @return list of clothes type
     */
    @GetMapping
    public ApiResponse<List<ClothesTypeResponse>> getClothesType() {
        List<ClothesTypeResponse> result = clothesTypeService.getAllClothesType();
        return ApiResponse.<List<ClothesTypeResponse>>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API get clothes type by id
     *
     * @return the clothes type with id
     */
    @GetMapping("/{clothes_type_id}")
    public ApiResponse<ClothesTypeResponse> getClothesTypeById(@PathVariable Integer clothes_type_id) {
        ClothesTypeResponse result = clothesTypeService.findClothesTypeById(clothes_type_id);
        return ApiResponse.<ClothesTypeResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new clothes type
     *
     * @param request -- create information
     * @return a new clothes type have been created
     */
    @PostMapping
    public ApiResponse<ClothesTypeResponse> createClothesType(@RequestBody @Valid ClothesTypeRequest request) {
        ClothesTypeResponse result = clothesTypeService.createClothesType(request);
        return ApiResponse.<ClothesTypeResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new clothes type
     *
     * @param clothes_type_id -- id of clothes type
     * @param request         -- update information
     * @return a new clothes type have been created
     */
    @PutMapping("/{clothes_type_id}")
    public ApiResponse<ClothesTypeResponse> updateClothesType(@PathVariable Integer clothes_type_id, @RequestBody @Valid ClothesTypeRequest request) {
        ClothesTypeResponse result = clothesTypeService.updateClothesType(clothes_type_id, request);
        return ApiResponse.<ClothesTypeResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API delete soft clothes type
     *
     * @param clothes_type_id -- id of clothes type
     * @return a new clothes type have been created
     */
    @DeleteMapping("/{clothes_type_id}")
    public ApiResponse<Void> deleteClothesType(@PathVariable Integer clothes_type_id) {
        clothesTypeService.deleteClothesType(clothes_type_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }

}
