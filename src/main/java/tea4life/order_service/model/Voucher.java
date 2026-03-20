package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.base.BaseEntity;

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
@SQLDelete(sql = "UPDATE vouchers SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Voucher extends BaseEntity {
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
    @Column(nullable = false, name = "img_url")
    String imgUrl;
    @Column(nullable = false, name="is_deleted")
    boolean isDeleted = false;

    @OneToMany(mappedBy = "voucher")
    Set<VoucherOrder> voucherOrders;
}
