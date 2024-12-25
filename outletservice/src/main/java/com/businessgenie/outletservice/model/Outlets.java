package com.businessgenie.outletservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "outlets")
public class Outlets {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "outlet_name", length = 255, nullable = false)
    private String outletName;

    @Column(name = "outlet_sale_point", length = 255, nullable = false)
    private String outletSalePoint;

    @Column(name = "client_id", length = 255)
    private String clientId;
}