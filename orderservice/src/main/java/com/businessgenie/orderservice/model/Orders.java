package com.businessgenie.orderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "comment_for_kot", length = 255)
    private String commentForKot;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "item_id", length = 255, nullable = false)
    private String itemId;

    @Column(name = "running_order_id", length = 255, nullable = false)
    private String runningOrderId;

    @Column(name = "client_id", length = 255, nullable = false)
    private String clientId;

    @Column(name = "order_placed", nullable = false, columnDefinition = "tinyint default 1")
    private boolean orderPlaced;

    @Column(name = "order_approved", nullable = false, columnDefinition = "tinyint default 0")
    private boolean orderApproved;

    @Column(name = "order_processed", nullable = false, columnDefinition = "tinyint default 0")
    private boolean orderProcessed;

    @Column(name = "order_prepared", nullable = false, columnDefinition = "tinyint default 0")
    private boolean orderPrepared;

    @Column(name = "kot_number", length = 255)
    private String kotNumber;

    @Column(name = "time_stamp", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime timeStamp;
}