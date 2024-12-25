package com.businessgenie.taxclassservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tax_class")
public class TaxClass {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "tax_class", length = 255, nullable = false)
    private String taxClass;

    @Column(name = "tax_rate", nullable = false)
    private double taxRate;

    @Column(name = "client_id", length = 255, nullable = false)
    private String clientId;

    @Column(name = "master_filter", length = 255)
    private String masterFilter;
}