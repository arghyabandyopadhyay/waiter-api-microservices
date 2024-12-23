package com.businessgenie.userclientallocations.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_client_allocation")
public class UserClientAllocation {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "company_guid", length = 255)
    private String companyGUID;

    @Column(name = "user_id", length = 255, nullable = false)
    private String userId;

    @Column(name = "outlet_id", length = 255)
    private String outletId;

    @Column(name = "uca_role_id", nullable = false, columnDefinition = "int default 1")
    private int ucaRoleId;
}