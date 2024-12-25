package com.businessgenie.menugroupservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_group")
public class MenuGroup {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "stock_group", length = 255)
    private String stockGroup;

    @Column(name = "outlet_id", length = 255)
    private String outletId;

    @Column(name = "client_id", length = 255)
    private String clientId;
}