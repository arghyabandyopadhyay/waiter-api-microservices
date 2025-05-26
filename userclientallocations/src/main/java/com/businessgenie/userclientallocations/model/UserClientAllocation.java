package com.businessgenie.userclientallocations.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.businessgenie.userclientallocations.dto.UserClientAllocationForUserDTO;
import com.businessgenie.userclientallocations.dto.ClientUserAllocationDTO;
import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_client_allocation")
@SqlResultSetMapping(
    name = "UserClientAllocationForUserDTOMapping",
    classes = @ConstructorResult(
        targetClass = UserClientAllocationForUserDTO.class,
        columns = {
            @ColumnResult(name = "id", type = String.class),
            @ColumnResult(name = "outlet_id", type = String.class),
            @ColumnResult(name = "client_id", type = String.class),
            @ColumnResult(name = "outlet_name", type = String.class),
            @ColumnResult(name = "outlet_sale_point", type = String.class),
            @ColumnResult(name = "client_name", type = String.class),
            @ColumnResult(name = "logo_url", type = String.class),
            @ColumnResult(name = "data_exchange_via", type = String.class),
            @ColumnResult(name = "data_exchange_url", type = String.class),
            @ColumnResult(name = "uca_role_id", type = Integer.class)
        }
    )
)
@SqlResultSetMapping(
    name = "ClientUserAllocationDTOMapping",
    classes = @ConstructorResult(
        targetClass = ClientUserAllocationDTO.class,
        columns = {
            @ColumnResult(name = "id", type = String.class),
            @ColumnResult(name = "user_client_allocation_id", type = String.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "mobile_number", type = String.class),
            @ColumnResult(name = "role_id", type = Integer.class),
            @ColumnResult(name = "is_active", type = Boolean.class),
            @ColumnResult(name = "last_login", type = LocalDateTime.class),
            @ColumnResult(name = "outlet_id", type = String.class),
            @ColumnResult(name = "outlet_name", type = String.class),
            @ColumnResult(name = "uca_role_id", type = Integer.class)
        }
    )
)
@NamedNativeQuery(
        name="UserClientAllocations.findAllocationsForUser",
        query = "SELECT	user_client_allocation.id as id, Outlets.id AS outlet_id,client_id,outlet_name,outlet_sale_point,clients.name AS client_name,logo_url,data_exchange_via,data_exchange_url,uca_role_id FROM user_client_allocation INNER JOIN Outlets ON user_client_allocation.outlet_id = Outlets.id INNER JOIN Clients ON Outlets.client_id = Clients.id WHERE user_id = :user_id",
        resultSetMapping = "UserClientAllocationForUserDTOMapping")

@NamedNativeQuery(
    name = "UserClientAllocations.findAllocationsForClient",
    query = "SELECT users.id AS id, user_client_allocation.id AS user_client_allocation_id, users.name, users.mobile_number, users.role_id, users.is_active, users.last_login, outlets.id AS outlet_id, outlets.outlet_name, user_client_allocation.uca_role_id " +
            "FROM clients " +
            "INNER JOIN outlets ON clients.id = outlets.client_id " +
            "INNER JOIN user_client_allocation ON outlets.id = user_client_allocation.outlet_id " +
            "INNER JOIN users ON user_client_allocation.user_id = users.id " +
            "WHERE clients.id = :client_id " +
            "ORDER BY user_client_allocation.outlet_id",
    resultSetMapping = "ClientUserAllocationDTOMapping"
)
public class UserClientAllocation {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "user_id", length = 255, nullable = false)
    private String userId;

    @Column(name = "outlet_id", length = 255)
    private String outletId;

    @Column(name = "uca_role_id", nullable = false, columnDefinition = "int default 1")
    private int ucaRoleId;
}