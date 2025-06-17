package com.example.clothes.Controller;

import com.example.clothes.DTO.RequestDTO.TypeDetailsRequest;
import com.example.clothes.DTO.ResponseDTO.ApiResponse;
import com.example.clothes.DTO.ResponseDTO.TypeDetailsResponse;
import com.example.clothes.Service.impl.TypeDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/type_details")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "https://clothes-fe-ten.vercel.app",
        "http://localhost:5173"
})
public class TypeDetailsController {

    private final TypeDetailsService typeDetailsService;

    /**
     * API get type details list
     *
     * @return list of type details
     */
    @GetMapping
    public ApiResponse<List<TypeDetailsResponse>> getTypeDetails() {
        List<TypeDetailsResponse> result = typeDetailsService.getAllTypeDetails();
        return ApiResponse.<List<TypeDetailsResponse>>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API get type details by id
     *
     * @return the type details with id
     */
    @GetMapping("/{type_detail_id}")
    public ApiResponse<TypeDetailsResponse> getTypeDetailsById(@PathVariable Integer type_detail_id) {
        TypeDetailsResponse result = typeDetailsService.findTypeDetailsById(type_detail_id);
        return ApiResponse.<TypeDetailsResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new type details
     *
     * @param request -- create information
     * @return a new type details have been created
     */
    @PostMapping
    public ApiResponse<TypeDetailsResponse> createTypeDetails(@RequestBody @Valid TypeDetailsRequest request) {
        TypeDetailsResponse result = typeDetailsService.createTypeDetails(request);
        return ApiResponse.<TypeDetailsResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new type details
     *
     * @param type_detail_id -- id of type details
     * @param request        -- update information
     * @return a new type details have been created
     */
    @PutMapping("/{type_detail_id}")
    public ApiResponse<TypeDetailsResponse> updateTypeDetails(@PathVariable Integer type_detail_id, @RequestBody @Valid TypeDetailsRequest request) {
        TypeDetailsResponse result = typeDetailsService.updateTypeDetails(type_detail_id, request);
        return ApiResponse.<TypeDetailsResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API delete soft type details
     *
     * @param type_detail_id -- id of type details
     * @return a new type details have been created
     */
    @DeleteMapping("/{type_detail_id}")
    public ApiResponse<Void> deleteTypeDetails(@PathVariable Integer type_detail_id) {
        typeDetailsService.deleteTypeDetails(type_detail_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }

}
