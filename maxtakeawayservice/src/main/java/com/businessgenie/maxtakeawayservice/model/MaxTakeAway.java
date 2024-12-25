package com.businessgenie.maxtakeawayservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "max_take_away")
public class MaxTakeAway {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "outlet_id", length = 255)
    private String outletId;

    @Column(name = "last_take_away", nullable = false)
    private int lastTakeAway;

    @Column(name = "current_date")
    private Date currentDate;
}