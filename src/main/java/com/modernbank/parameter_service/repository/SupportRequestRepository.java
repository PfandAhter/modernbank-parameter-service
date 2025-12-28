package com.modernbank.parameter_service.repository;

import com.modernbank.parameter_service.entity.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRequestRepository extends JpaRepository<SupportRequest, Long > {

}