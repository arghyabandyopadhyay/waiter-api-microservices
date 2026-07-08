package com.businessgenie.menuservice.model;

import com.businessgenie.menuservice.dto.MenuItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_item")
@SqlResultSetMapping(
    name = "MenuItemDTOMapping",
    classes = @ConstructorResult(
        targetClass = MenuItemDTO.class,
        columns = {
            @ColumnResult(name = "id", type = String.class),
            @ColumnResult(name = "item_image", type = String.class),
            @ColumnResult(name = "item", type = String.class),
            @ColumnResult(name = "item_description", type = String.class),
            @ColumnResult(name = "comment_for_kot", type = String.class),
            @ColumnResult(name = "stock_group_id", type = String.class),
            @ColumnResult(name = "rate_before_discount", type = Double.class),
            @ColumnResult(name = "discount", type = Double.class),
            @ColumnResult(name = "rate", type = Double.class),
            @ColumnResult(name = "tax_class_id", type = String.class),
            @ColumnResult(name = "is_discountable", type = Boolean.class),
            @ColumnResult(name = "is_veg", type = Boolean.class),
            @ColumnResult(name = "tax_rate", type = Double.class),
            @ColumnResult(name = "tags", type = String.class),
            @ColumnResult(name = "client_id", type = String.class),
            @ColumnResult(name = "favourite", type = Boolean.class),
            @ColumnResult(name = "image_url", type = String.class),
            @ColumnResult(name = "stock_group", type = String.class),
            @ColumnResult(name = "outlet_id", type = String.class),
            @ColumnResult(name = "outlet_name", type = String.class)
        }
    )
)
@NamedNativeQuery(
    name = "MenuItem.findByOutletId",
    query = "SELECT menu_item.id, item_image, item, item_description, comment_for_kot, menu_group.id as stock_group_id, rate_before_discount, discount, rate, tax_class_id, is_discountable, is_veg, tax_rate, tags, menu_item.client_id, favourite, image_url, stock_group, outlets.id as outlet_id, outlet_name  " +
            "FROM menu_item " +
            "INNER JOIN menu_group ON menu_item.stock_group_id = menu_group.id " +
            "INNER JOIN tax_class ON menu_item.tax_class_id = tax_class.id " +
            "INNER JOIN outlets ON menu_item.outlet_id = outlets.id " +
            "WHERE menu_item.outlet_id = :outlet_id",
    resultSetMapping = "MenuItemDTOMapping"
)
@NamedNativeQuery(
    name = "MenuItem.findByClientId",
    query = "SELECT menu_item.id, item_image, item, item_description, comment_for_kot, menu_group.id as stock_group_id, rate_before_discount, discount, rate, tax_class_id, is_discountable, is_veg, tax_rate, tags, menu_item.client_id, favourite, image_url, stock_group, outlets.id as outlet_id, outlet_name " +
            "FROM menu_item " +
            "INNER JOIN menu_group ON menu_item.stock_group_id = menu_group.id " +
            "INNER JOIN tax_class ON menu_item.tax_class_id = tax_class.id " +
            "INNER JOIN outlets ON menu_item.outlet_id = outlets.id " +
            "WHERE menu_item.client_id = :client_id",
    resultSetMapping = "MenuItemDTOMapping"
)
@NamedNativeQuery(
    name = "MenuItem.findByUserId",
    query = "SELECT menu_item.id, item_image, item, item_description, comment_for_kot, menu_group.id as stock_group_id, rate_before_discount, discount, rate, tax_class_id, is_discountable, is_veg, tax_rate, tags, menu_item.client_id, IF((menu_item.id in (SELECT menu_id FROM favourites WHERE user_id = :user_id)), 1, 0) AS favourite, image_url, stock_group, outlets.id as outlet_id, outlet_name  " +
            "FROM menu_item " +
            "INNER JOIN menu_group ON menu_item.stock_group_id = menu_group.id " +
            "INNER JOIN tax_class ON menu_item.tax_class_id = tax_class.id " +
            "INNER JOIN outlets ON menu_item.outlet_id = outlets.id " +
            "WHERE menu_item.outlet_id = :outlet_id",
    resultSetMapping = "MenuItemDTOMapping"
)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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