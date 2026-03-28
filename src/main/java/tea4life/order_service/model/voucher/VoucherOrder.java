package tea4life.order_service.model.voucher;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.base.BaseEntity;
import tea4life.order_service.model.order.Order;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 10:23 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "voucher_orders")
@SQLDelete(sql = "UPDATE voucher_orders SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoucherOrder extends BaseEntity {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(name = "discount_amount", nullable = false)
    BigDecimal discountAmount;
    @Column(nullable = false, name = "is_deleted")
    boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    Voucher voucher;
}
