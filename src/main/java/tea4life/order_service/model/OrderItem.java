package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tea4life.order_service.config.database.SnowflakeGenerated;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 11:07 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(name = "product_id", nullable = false)
    Long productId;
    Integer quantity;
    @Column(name = "unit_price", nullable = false)
    BigDecimal unitPrice;
    @Column(name = "sub_total", nullable = false)
    BigDecimal subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
}
