package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tea4life.order_service.config.database.SnowflakeGenerated;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 10:14 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "vouchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Voucher {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(nullable = false, name = "discount_percentage")
    double discountPercentage;
    @Column(nullable = false, name = "min_order_amount")
    BigDecimal minOrderAmount;
    @Column(nullable = false, name = "max_discount_amount")
    BigDecimal maxDiscountAmount;
    String description;

    @OneToMany(mappedBy = "voucher")
    Set<VoucherOrder> voucherOrders;
}
