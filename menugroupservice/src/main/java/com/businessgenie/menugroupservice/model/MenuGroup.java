package com.businessgenie.menugroupservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.businessgenie.menugroupservice.dto.MenuGroupDTO;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_group")
@SqlResultSetMapping(
    name = "MenuGroupDTOMapping",
    classes = @ConstructorResult(
        targetClass = MenuGroupDTO.class,
        columns = {
            @ColumnResult(name = "id", type = String.class),
            @ColumnResult(name = "image_url", type = String.class),
            @ColumnResult(name = "stock_group", type = String.class),
            @ColumnResult(name = "outlet_id", type = String.class),
            @ColumnResult(name = "outlet_name", type = String.class)
        }
    )
)
@NamedNativeQuery(
    name = "MenuGroup.findByOutletId",
    query = "SELECT menu_group.id, menu_group.image_url, menu_group.stock_group, menu_group.outlet_id, outlets.outlet_name " +
            "FROM menu_group " +
            "INNER JOIN outlets ON menu_group.outlet_id = outlets.id " +
            "WHERE menu_group.outlet_id = :outlet_id",
    resultSetMapping = "MenuGroupDTOMapping"
)
@NamedNativeQuery(
    name = "MenuGroup.findByClientId",
    query = "SELECT menu_group.id, menu_group.image_url, menu_group.stock_group, menu_group.outlet_id, outlets.outlet_name " +
            "FROM menu_group " +
            "INNER JOIN outlets ON menu_group.outlet_id = outlets.id " +
            "WHERE menu_group.client_id = :client_id",
    resultSetMapping = "MenuGroupDTOMapping"
)
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