package com.example.clothes.Repository;

import com.example.clothes.Entity.ClothesVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesVariantsRepository extends JpaRepository<ClothesVariants, Integer> {
    List<ClothesVariants> findByIsDeletedFalse();
}
