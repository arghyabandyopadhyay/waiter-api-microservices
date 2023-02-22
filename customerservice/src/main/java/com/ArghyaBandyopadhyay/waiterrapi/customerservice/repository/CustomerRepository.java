package com.ArghyaBandyopadhyay.waiterrapi.customerservice.repository;

import com.ArghyaBandyopadhyay.waiterrapi.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByEmailId(String emailId);

    @Query(value = "SELECT * FROM customer WHERE acc_no= :accountNo AND ifsc_code= :ifscCode",
            nativeQuery = true)
    Customer findByAccountDetails(@Param("accountNo") Long accountNo, @Param("ifscCode")String ifscCode);
}