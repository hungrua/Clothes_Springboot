package com.example.clothes.Repository;

import com.example.clothes.Entity.Size;
import com.example.clothes.Entity.TypeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findByIsDeletedFalse();
}
