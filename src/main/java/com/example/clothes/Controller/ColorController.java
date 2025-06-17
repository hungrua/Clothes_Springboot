package com.example.clothes.Controller;

import com.example.clothes.DTO.RequestDTO.ColorRequest;
import com.example.clothes.DTO.ResponseDTO.ApiResponse;
import com.example.clothes.DTO.ResponseDTO.ColorResponse;
import com.example.clothes.Service.impl.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/colors")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "https://clothes-fe-seven.vercel.app",
        "http://localhost:5173"
})
public class ColorController {

    private final ColorService colorService;

    /**
     * API get color list
     *
     * @return list of color
     */
    @GetMapping
    public ApiResponse<List<ColorResponse>> getColor() {
        List<ColorResponse> result = colorService.getAllColor();
        return ApiResponse.<List<ColorResponse>>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API get color by id
     *
     * @return the color with id
     */
    @GetMapping("/{color_id}")
    public ApiResponse<ColorResponse> getColorById(@PathVariable Integer color_id) {
        ColorResponse result = colorService.findColorById(color_id);
        return ApiResponse.<ColorResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new color
     *
     * @param request -- create information
     * @return a new color have been created
     */
    @PostMapping
    public ApiResponse<ColorResponse> createColor(@RequestBody @Valid ColorRequest request) {
        ColorResponse result = colorService.createColor(request);
        return ApiResponse.<ColorResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API create a new color
     *
     * @param color_id -- id of color
     * @param request  -- update information
     * @return a new color have been created
     */
    @PutMapping("/{color_id}")
    public ApiResponse<ColorResponse> updateColor(@PathVariable Integer color_id, @RequestBody @Valid ColorRequest request) {
        ColorResponse result = colorService.updateColor(color_id, request);
        return ApiResponse.<ColorResponse>builder()
                .code(200)
                .message("Success")
                .result(result)
                .build();
    }

    /**
     * API delete soft color
     *
     * @param color_id -- id of color
     * @return a new color have been created
     */
    @DeleteMapping("/{color_id}")
    public ApiResponse<Void> deleteColor(@PathVariable Integer color_id) {
        colorService.deleteColor(color_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Success")
                .build();
    }

}
