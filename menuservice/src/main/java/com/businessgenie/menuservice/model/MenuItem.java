package com.businessgenie.menuservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "item_image", length = 255)
    private String itemImage;

    @Column(name = "item", length = 255, nullable = false)
    private String item;

    @Column(name = "item_description", length = 255)
    private String itemDescription;

    @Column(name = "comment_for_kot", length = 255)
    private String commentForKot;

    @Column(name = "stock_group_id", length = 255, nullable = false)
    private String stockGroupId;

    @Column(name = "rate_before_discount", nullable = false)
    private double rateBeforeDiscount;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "tax_class_id", length = 255, nullable = false)
    private String taxClassId;

    @Column(name = "is_discountable", nullable = false)
    private boolean isDiscountable;

    @Column(name = "is_veg", nullable = false)
    private boolean isVeg;

    @Column(name = "tags", length = 255)
    private String tags;

    @Column(name = "client_id", length = 255, nullable = false)
    private String clientId;

    @Column(name = "favourite", nullable = false)
    private boolean favourite;

    @Column(name = "outlet_id", length = 255, nullable = false)
    private String outletId;
}