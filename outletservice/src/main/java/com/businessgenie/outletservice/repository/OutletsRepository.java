package com.businessgenie.outletservice.repository;

import com.businessgenie.outletservice.model.Outlets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutletsRepository extends JpaRepository<Outlets, UUID> {
    List<Outlets> findByClientId(String clientId);
}