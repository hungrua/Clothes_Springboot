package com.example.clothes.Repository;

import com.example.clothes.Entity.ClothesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesTypeRepository extends JpaRepository<ClothesType, Integer> {
    List<ClothesType> findByIsDeletedFalse();

    boolean existsByCode(String code);
}
