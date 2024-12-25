package com.businessgenie.clientservice.repository;

import com.businessgenie.clientservice.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClientsRepository extends JpaRepository<Clients, UUID> {
    @Query(value = "SELECT * FROM clients WHERE name = :name AND address = :address",
            nativeQuery = true)
    Clients findClient(@Param("name") String name, @Param("address") String address);
}