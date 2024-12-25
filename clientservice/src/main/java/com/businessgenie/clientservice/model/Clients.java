package com.businessgenie.clientservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.businessgenie.clientservice.dto.ClientUserAllocationDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Clients")
@SqlResultSetMapping(
    name = "ClientUserAllocationDTOMapping",
    classes = @ConstructorResult(
        targetClass = ClientUserAllocationDTO.class,
        columns = {
            @ColumnResult(name = "id", type = UUID.class),
            @ColumnResult(name = "user_client_allocation_id", type = UUID.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "mobile_number", type = String.class),
            @ColumnResult(name = "role_id", type = Integer.class),
            @ColumnResult(name = "is_active", type = Boolean.class),
            @ColumnResult(name = "last_login", type = LocalDateTime.class),
            @ColumnResult(name = "outlet_id", type = UUID.class),
            @ColumnResult(name = "outlet_name", type = String.class),
            @ColumnResult(name = "uca_role_id", type = Integer.class)
        }
    )
)
@NamedNativeQuery(
        name="Client.findAllWaiters",
        query = "SELECT Users.id AS id, user_client_allocation.id AS user_client_allocation_id, Users.name as name, mobile_number, role_id, is_active, last_login, Outlets.id AS outlet_id, outlet_name, uca_role_id FROM Clients INNER JOIN Outlets ON Clients.id=Outlets.client_id INNER JOIN user_client_allocation ON Outlets.id=user_client_allocation.outlet_id INNER JOIN Users ON user_client_allocation.user_id=Users.id WHERE (Clients.id= :client_id) Order By user_client_allocation.outlet_id",
        resultSetMapping = "ClientUserAllocationDTOMapping")
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