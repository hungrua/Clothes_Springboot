package com.example.clothes.Repository;

import com.example.clothes.Entity.TypeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TypeDetailsRepository extends JpaRepository<TypeDetails, Integer> {
    List<TypeDetails> findByIsDeletedFalse();

    List<TypeDetails> findAllByIdIn(Set<Integer> ids);

    boolean existsByCode(String code);
}
