package com.modernbank.parameter_service.repository;

import com.modernbank.parameter_service.entity.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ErrorCodeRepository extends JpaRepository<ErrorCode,String> {

    @Query("SELECT e FROM ErrorCode e WHERE e.id =?1")
    Optional<ErrorCode> findByCode(String code);
}