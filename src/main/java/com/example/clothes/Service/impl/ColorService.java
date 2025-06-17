package com.example.clothes.Service.impl;

import com.example.clothes.DTO.RequestDTO.ColorRequest;
import com.example.clothes.DTO.ResponseDTO.ColorResponse;
import com.example.clothes.Entity.Color;
import com.example.clothes.Exception.AppException;
import com.example.clothes.Exception.ErrorCode;
import com.example.clothes.Mapper.ColorMapper;
import com.example.clothes.Repository.ColorRepository;
import com.example.clothes.Service.IColorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ColorService implements IColorService {
    public static final Logger log = LogManager.getLogger();
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ColorMapper colorMapper;

    /**
     * get list of all color that is not deleted
     *
     * @return list of all color that is not deleted
     */
    @Override
    public List<ColorResponse> getAllColor() {
        List<Color> colorList = colorRepository.findByIsDeletedFalse();
        List<ColorResponse> colorResponseList = new ArrayList<>();
        for (Color color : colorList) {
            colorResponseList.add(colorMapper.toColorResponse(color));
        }
        return colorResponseList;
    }

    /**
     * Get the color by its it
     *
     * @param id -- color id
     * @return color have the id
     */
    @Override
    public ColorResponse findColorById(Integer id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Không tìm thấy màu có id {} trong hệ thống", id);
                    return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
                });
        return colorMapper.toColorResponse(color);
    }

    /**
     * Create new color
     *
     * @param request -- new color information
     * @return new color
     */
    @Override
    public ColorResponse createColor(ColorRequest request) {
        Color color = colorMapper.toColor(request);
        if (colorRepository.existsByColorCode(request.getColorCode())) {
            log.error("Không thể tạo mới màu này do mã màu {} đã tồn tại trong hệ thống", request.getColorCode());
            throw new AppException(ErrorCode.DUPLICATE_FIELD_CODE);
        }
        color.setDeleted(false);
        color.setCreatedAt(new Date());
        Color newColor = colorRepository.save(color);
        log.info("Tạo mới màu với mã {} thành công", newColor.getColorCode());
        return colorMapper.toColorResponse(newColor);
    }

    /**
     * Create new color
     *
     * @param id      -- id of color is updated
     * @param request -- color information needed to update
     * @return updated color
     */
    @Override
    public ColorResponse updateColor(Integer id, ColorRequest request) {
        Color color = colorRepository.findById(id).orElseThrow(() -> {
            log.error("Không tìm thấy màu có id {} trong hệ thống để cập nhật", id);
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        colorMapper.updateColor(color, request);
        color.setUpdatedAt(new Date());
        color = colorRepository.save(color);
        log.info("Cập nhật màu với mã {} thành công", color.getColorCode());
        return colorMapper.toColorResponse(color);
    }

    /**
     * @param id -- id of color needed to be deleted
     */
    @Override
    public void deleteColor(Integer id) {
        Color color = colorRepository.findById(id).orElseThrow(() -> {
            log.error("Không tìm thấy màu có id {} trong hệ thống để xóa", id);
            return new AppException(ErrorCode.PARAMETER_NOT_FOUND);
        });
        color.setDeleted(true);
        colorRepository.save(color);
        log.info("Xóa màu với mã {} thành công", color.getColorCode());
    }
}
