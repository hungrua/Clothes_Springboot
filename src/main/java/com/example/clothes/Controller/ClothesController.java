package com.example.clothes.Controller;

import com.example.clothes.DTO.RequestDTO.ClothesRequest;
import com.example.clothes.DTO.ResponseDTO.ApiResponse;
import com.example.clothes.DTO.ResponseDTO.ClothesResponse;
import com.example.clothes.Service.impl.ClothesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clothes")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "https://clothes-fe-seven.vercel.app",
        "http://localhost:5173"
})
public class ClothesController {

    private final ClothesService clothesService;

    /**
     * API get clothes list
     *
     * @return list of clothes
     */
    @GetMapping
    public ApiResponse<List<ClothesResponse>> getClothes() {
        List<ClothesResponse> result = clothesService.getAllClothes();
        return ApiResponse.<List<ClothesResponse>>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API get clothes by id
     *
     * @return the clothes with id
     */
    @GetMapping("/{clothes_id}")
    public ApiResponse<ClothesResponse> getClothesById(@PathVariable Integer clothes_id) {
        ClothesResponse result = clothesService.findClothesById(clothes_id);
        return ApiResponse.<ClothesResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new clothes
     *
     * @param request -- create information
     * @return a new clothes have been created
     */
    @PostMapping
    public ApiResponse<ClothesResponse> createClothes(@RequestBody @Valid ClothesRequest request) {
        ClothesResponse result = clothesService.createClothes(request);
        return ApiResponse.<ClothesResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new clothes
     *
     * @param clothes_id -- id of clothes
     * @param request    -- update information
     * @return a new clothes have been created
     */
    @PutMapping("/{clothes_id}")
    public ApiResponse<ClothesResponse> updateClothes(@PathVariable Integer clothes_id, @RequestBody @Valid ClothesRequest request) {
        ClothesResponse result = clothesService.updateClothes(clothes_id, request);
        return ApiResponse.<ClothesResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API delete soft clothes
     *
     * @param clothes_id -- id of clothes
     * @return a new clothes have been created
     */
    @DeleteMapping("/{clothes_id}")
    public ApiResponse<Void> deleteClothes(@PathVariable Integer clothes_id) {
        clothesService.deleteClothes(clothes_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }

}
