package com.example.clothes.Controller;

import com.example.clothes.DTO.RequestDTO.SizeRequest;
import com.example.clothes.DTO.ResponseDTO.ApiResponse;
import com.example.clothes.DTO.ResponseDTO.SizeResponse;
import com.example.clothes.Service.impl.SizeService;
import com.example.clothes.Service.impl.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sizes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SizeController {

    private final SizeService sizeService;

    /**
     * API get size list
     *
     * @return list of size
     */
    @GetMapping
    public ApiResponse<List<SizeResponse>> getSize() {
        List<SizeResponse> result = sizeService.getAllSize();
        return ApiResponse.<List<SizeResponse>>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API get size by id
     *
     * @return the size with id
     */
    @GetMapping("/{size_id}")
    public ApiResponse<SizeResponse> getSizeById(@PathVariable Integer size_id) {
        SizeResponse result = sizeService.findSizeById(size_id);
        return ApiResponse.<SizeResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new size
     *
     * @param request -- create information
     * @return a new size have been created
     */
    @PostMapping
    public ApiResponse<SizeResponse> createSize(@RequestBody @Valid SizeRequest request) {
        SizeResponse result = sizeService.createSize(request);
        return ApiResponse.<SizeResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new size
     *
     * @param size_id -- id of size
     * @param request -- update information
     * @return a new size have been created
     */
    @PutMapping("/{size_id}")
    public ApiResponse<SizeResponse> updateSize(@PathVariable Integer size_id, @RequestBody @Valid SizeRequest request) {
        SizeResponse result = sizeService.updateSize(size_id, request);
        return ApiResponse.<SizeResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API delete soft size
     *
     * @param size_id -- id of size
     * @return a new size have been created
     */
    @DeleteMapping("/{size_id}")
    public ApiResponse<Void> deleteSize(@PathVariable Integer size_id) {
        sizeService.deleteSize(size_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }

}
