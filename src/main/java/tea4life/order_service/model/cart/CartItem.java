package tea4life.order_service.model.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 10:37 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem extends BaseEntity {
    @SnowflakeGenerated
    @Id
    Long id;

    // Product Snapshot
    @Column(nullable = false, name = "product_id")
    Long productId;

    @Column(nullable = false, name = "product_name", length = 200)
    String productName;

    @Column(name = "product_image_url", length = 500)
    String productImageUrl;

    @Lob
    @Column(name = "selected_options_snapshot", columnDefinition = "LONGTEXT")
    String selectedOptionsSnapshot;

    @Column(nullable = false, name = "unit_price")
    BigDecimal unitPrice;

    @Column(nullable = false, name = "sub_total")
    BigDecimal subTotal;

    @Column(nullable = false)
    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    Cart cart;

}
