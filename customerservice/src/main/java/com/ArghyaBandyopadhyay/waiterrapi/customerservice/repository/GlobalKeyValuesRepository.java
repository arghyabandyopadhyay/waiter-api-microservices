package com.ArghyaBandyopadhyay.waiterrapi.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GlobalKeyValuesRepository extends JpaRepository<GlobalKeyValues, UUID>{
    Optional<GlobalKeyValues> findByKeyString(String keyString);
}
