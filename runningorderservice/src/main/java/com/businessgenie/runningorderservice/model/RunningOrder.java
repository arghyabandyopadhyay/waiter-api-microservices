package com.businessgenie.runningorderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "running_order")
public class RunningOrder {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "mobile_no", length = 255, nullable = false)
    private String mobileNo;

    @Column(name = "sale_point_type", length = 255, nullable = false)
    private String salePointType;

    @Column(name = "sale_point_name", length = 255, nullable = false)
    private String salePointName;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "pax")
    private Integer pax;

    @Column(name = "active_since", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime activeSince;

    @Column(name = "bill_printed", nullable = false)
    private boolean billPrinted;

    @Column(name = "outlet_id", length = 255)
    private String outletId;

    @Column(name = "waiter_id", length = 255, nullable = false)
    private String waiterId;

    @Column(name = "client_id", length = 255)
    private String clientId;

    @Column(name = "kot_numbers", length = 255, nullable = false)
    private String kotNumbers;

    @Column(name = "is_terminated", nullable = false)
    private boolean isTerminated;

    @Column(name = "time_stamp", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime timeStamp;
}