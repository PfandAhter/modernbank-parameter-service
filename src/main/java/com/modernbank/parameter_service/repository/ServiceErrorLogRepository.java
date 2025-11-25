package com.modernbank.parameter_service.repository;

import com.modernbank.parameter_service.entity.ServiceErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceErrorLogRepository extends JpaRepository<ServiceErrorLog,String> {

}