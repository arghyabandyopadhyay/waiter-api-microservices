package com.businessgenie.taxclassservice.repository;

import com.businessgenie.taxclassservice.model.TaxClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaxClassRepository extends JpaRepository<TaxClass, UUID> {
    List<TaxClass> findByClientId(String clientId);
}