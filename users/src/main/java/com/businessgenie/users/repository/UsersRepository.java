package com.businessgenie.users.repository;

import com.businessgenie.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Users findByEmailId(String emailId);
    Users findByMobileNumber(String mobileNumber);
    @Query(value = "SELECT * FROM Users WHERE MasterSearchFilter like '%:searchText%'",
            nativeQuery = true)
    List<Users> findBySearchText(@Param("searchText") String searchText);
}