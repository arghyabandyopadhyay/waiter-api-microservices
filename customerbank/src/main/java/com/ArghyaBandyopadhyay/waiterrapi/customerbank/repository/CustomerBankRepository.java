package com.ArghyaBandyopadhyay.waiterrapi.customerbank.repository;

import com.ArghyaBandyopadhyay.waiterrapi.customerbank.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerBankRepository extends JpaRepository<CustomerDetails, UUID> {
    Optional<CustomerDetails> findByMobileNumber(String mobileNo);
}