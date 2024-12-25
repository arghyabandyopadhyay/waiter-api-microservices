package com.businessgenie.clientservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Clients")
public class Clients {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "logo_url", length = 255)
    private String logoUrl;
    @Column(name = "data_exchange_via", length = 255)
    private String dataExchangeVia;
    @Column(name = "data_exchange_url", length = 255)
    private String dataExchangeUrl;
    @Column(name = "address", length = 255)
    private String address;
}