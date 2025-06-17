package com.example.clothes.Repository;

import com.example.clothes.Entity.ClothesType;
import com.example.clothes.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findByIsDeletedFalse();

    boolean existsByColorCode(String code);
}
