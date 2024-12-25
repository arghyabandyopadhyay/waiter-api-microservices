package com.businessgenie.userclientallocations.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.businessgenie.userclientallocations.dto.UserClientAllocationForUserDTO;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_client_allocation")
@SqlResultSetMapping(
    name = "UserClientAllocationForUserDTOMapping",
    classes = @ConstructorResult(
        targetClass = UserClientAllocationForUserDTO.class,
        columns = {
            @ColumnResult(name = "id", type = UUID.class),
            @ColumnResult(name = "outlet_id", type = UUID.class),
            @ColumnResult(name = "client_id", type = UUID.class),
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
@NamedNativeQuery(
        name="UserClientAllocations.findAllocationsForUser",
        query = "SELECT	user_client_allocation.id as id, Outlets.id AS outlet_id,client_id,outlet_name,outlet_sale_point,clients.name AS client_name,logo_url,data_exchange_via,data_exchange_url,uca_role_id FROM user_client_allocation INNER JOIN Outlets ON user_client_allocation.outlet_id = Outlets.id INNER JOIN Clients ON Outlets.client_id = Clients.id WHERE user_id = :user_id",
        resultSetMapping = "UserClientAllocationForUserDTOMapping")
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