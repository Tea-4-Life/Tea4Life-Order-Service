package tea4life.order_service.model.order;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.voucher.VoucherOrder;
import tea4life.order_service.model.base.BaseEntity;
import tea4life.order_service.model.constant.OrderStatus;
import tea4life.order_service.model.payment.Payment;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 10:05 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model.base
 */

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    @Id
    @SnowflakeGenerated
    Long id;

    @Enumerated(EnumType.STRING)
    OrderStatus status;
    @Column(nullable = false, name = "user_id")
    String keycloakId;
    String note;
    @Column(nullable = false, name = "price_before_discount")
    BigDecimal priceBeforeDiscount;
    @Column(nullable = false, name = "final_price")
    BigDecimal finalPrice;
    @Column(nullable = false, name = "is_deleted")
    boolean isDeleted = false;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<VoucherOrder> voucherOrders;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderItem> orderItems;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Payment payment;
}
