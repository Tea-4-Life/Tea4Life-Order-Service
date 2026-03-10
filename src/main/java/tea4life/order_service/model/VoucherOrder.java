package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tea4life.order_service.config.database.SnowflakeGenerated;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 10:23 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "voucher_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoucherOrder {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(name = "discount_amount", nullable = false)
    BigDecimal discountAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    Voucher voucher;
}
