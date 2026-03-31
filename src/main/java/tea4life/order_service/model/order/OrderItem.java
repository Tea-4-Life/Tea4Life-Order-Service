package tea4life.order_service.model.order;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 11:07 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "order_items")
@SQLDelete(sql = "UPDATE order_items SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem extends BaseEntity {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(name = "product_id", nullable = false)
    Long productId;
    @Column(name = "product_name", nullable = false)
    String productName;
    @Column(name = "product_image_url")
    String productImageUrl;
    @Lob
    @Column(name = "selected_options_snapshot", columnDefinition = "LONGTEXT")
    String selectedOptionsSnapshot;
    Integer quantity;
    @Column(name = "unit_price", nullable = false)
    BigDecimal unitPrice;
    @Column(name = "sub_total", nullable = false)
    BigDecimal subTotal;
    @Column(nullable = false, name = "is_deleted")
    boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
}
