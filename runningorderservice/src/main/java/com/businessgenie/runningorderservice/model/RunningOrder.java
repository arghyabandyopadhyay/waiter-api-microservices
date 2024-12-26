package com.businessgenie.runningorderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.businessgenie.runningorderservice.dto.RunningModelUserDetailOutletDetailDTO;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "running_order")
@SqlResultSetMapping(
    name = "RunningModelUserDetailOutletDetailDTOMapping",
    classes = @ConstructorResult(
        targetClass = RunningModelUserDetailOutletDetailDTO.class,
        columns = {
            @ColumnResult(name = "id", type = String.class),
            @ColumnResult(name = "is_terminated", type = Boolean.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "mobile_no", type = String.class),
            @ColumnResult(name = "sale_point_type", type = String.class),
            @ColumnResult(name = "sale_point_name", type = String.class),
            @ColumnResult(name = "amount", type = Double.class),
            @ColumnResult(name = "pax", type = Integer.class),
            @ColumnResult(name = "active_since", type = LocalDateTime.class),
            @ColumnResult(name = "bill_printed", type = Boolean.class),
            @ColumnResult(name = "outlet_name", type = String.class),
            @ColumnResult(name = "outlet_id", type = String.class),
            @ColumnResult(name = "waiter_mobile_no", type = String.class),
            @ColumnResult(name = "waiter_name", type = String.class)
        }
    )
)
@NamedNativeQuery(
    name = "RunningOrder.findRunningOrderByClientId", 
    query = "SELECT running_order.id, is_terminated, running_order.name as name, mobile_no , sale_point_type, sale_point_name, amount, pax, active_since, bill_printed, outlet_name, outlet_id, mobile_number as waiter_mobile_no, Users.name as waiter_name FROM running_order INNER JOIN users On running_order.waiter_id=Users.id INNER JOIN Outlets ON running_order.outlet_id=Outlets.id WHERE running_order.client_id=:clientId AND is_terminated=0 ORDER BY active_since Desc",
    resultSetMapping = "RunningModelUserDetailOutletDetailDTOMapping")
@NamedNativeQuery(
    name = "RunningOrder.findRunningOrderByClientIdOutletIdSalePointTypeAndSalePointName", 
    query = "SELECT running_order.id, is_terminated, running_order.name as name, mobile_no , sale_point_type, sale_point_name, amount, pax, active_since, bill_printed, outlet_name, outlet_id, mobile_number as waiter_mobile_no, Users.name as waiter_name FROM running_order INNER JOIN users On running_order.waiter_id=Users.id INNER JOIN Outlets ON running_order.outlet_id=Outlets.id WHERE running_order.client_id=:clientId AND outlet_name=:outletName AND sale_point_type=:salePointType AND sale_point_name=:salePointName AND is_terminated=0 ORDER BY active_since Desc",
    resultSetMapping = "RunningModelUserDetailOutletDetailDTOMapping")
@NamedNativeQuery(
    name = "RunningOrder.findRunningOrderByWaiterId", 
    query = "SELECT running_order.id, is_terminated, running_order.name as name, mobile_no , sale_point_type, sale_point_name, amount, pax, active_since, bill_printed, outlet_name, outlet_id, mobile_number as waiter_mobile_no, Users.name as waiter_name FROM running_order INNER JOIN users On running_order.waiter_id=Users.id INNER JOIN Outlets ON running_order.outlet_id=Outlets.id WHERE waiter_id=:waiterId ORDER BY is_terminated Desc",
    resultSetMapping = "RunningModelUserDetailOutletDetailDTOMapping")
@NamedNativeQuery(
    name = "RunningOrder.findRunningOrderByWaiterIdAndTerminationStatus", 
    query = "SELECT running_order.id, is_terminated, running_order.name as name, mobile_no , sale_point_type, sale_point_name, amount, pax, active_since, bill_printed, outlet_name, outlet_id, mobile_number as waiter_mobile_no, Users.name as waiter_name FROM running_order INNER JOIN users On running_order.waiter_id=Users.id INNER JOIN Outlets ON running_order.outlet_id=Outlets.id WHERE waiter_id=:waiterId AND is_terminated=:isTerminated ORDER BY active_since Desc",
    resultSetMapping = "RunningModelUserDetailOutletDetailDTOMapping")
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