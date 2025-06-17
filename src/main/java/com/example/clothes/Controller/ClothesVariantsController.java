package com.example.clothes.Controller;

import com.example.clothes.DTO.RequestDTO.ClothesVariantsRequest;
import com.example.clothes.DTO.ResponseDTO.ApiResponse;
import com.example.clothes.DTO.ResponseDTO.ClothesVariantsResponse;
import com.example.clothes.Service.impl.ClothesVariantsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clothes_variants")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://clothes-fe-seven.vercel.app/")
public class ClothesVariantsController {

    private final ClothesVariantsService clothesVariantService;

    /**
     * API get clothes_variant list
     *
     * @return list of clothes_variant
     */
    @GetMapping
    public ApiResponse<List<ClothesVariantsResponse>> getClothesVariants() {
        List<ClothesVariantsResponse> result = clothesVariantService.getAllClothesVariants();
        return ApiResponse.<List<ClothesVariantsResponse>>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API get clothes_variant by id
     *
     * @return the clothes_variant with id
     */
    @GetMapping("/{clothes_variant_id}")
    public ApiResponse<ClothesVariantsResponse> getClothesVariantsById(@PathVariable Integer clothes_variant_id) {
        ClothesVariantsResponse result = clothesVariantService.findClothesVariantsById(clothes_variant_id);
        return ApiResponse.<ClothesVariantsResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new clothes_variant
     *
     * @param request -- create information
     * @return a new clothes_variant have been created
     */
    @PostMapping
    public ApiResponse<ClothesVariantsResponse> createClothesVariants(@RequestBody @Valid ClothesVariantsRequest request) {
        ClothesVariantsResponse result = clothesVariantService.createClothesVariants(request);
        return ApiResponse.<ClothesVariantsResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new clothes_variant
     *
     * @param clothes_variant_id -- id of clothes_variant
     * @param request            -- update information
     * @return a new clothes_variant have been created
     */
    @PutMapping("/{clothes_variant_id}")
    public ApiResponse<ClothesVariantsResponse> updateClothesVariants(@PathVariable Integer clothes_variant_id, @RequestBody @Valid ClothesVariantsRequest request) {
        ClothesVariantsResponse result = clothesVariantService.updateClothesVariants(clothes_variant_id, request);
        return ApiResponse.<ClothesVariantsResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API delete soft clothes_variant
     *
     * @param clothes_variant_id -- id of clothes_variant
     * @return a new clothes_variant have been created
     */
    @DeleteMapping("/{clothes_variant_id}")
    public ApiResponse<Void> deleteClothesVariants(@PathVariable Integer clothes_variant_id) {
        clothesVariantService.deleteClothesVariants(clothes_variant_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }
}
