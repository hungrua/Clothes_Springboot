package com.example.clothes.Repository;

import com.example.clothes.Entity.Clothes;
import com.example.clothes.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    List<Clothes> findByIsDeletedFalse();

    boolean existsByClothesCode(String code);
}
