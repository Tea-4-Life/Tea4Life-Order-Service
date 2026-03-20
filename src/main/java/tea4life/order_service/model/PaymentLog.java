package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.base.BaseEntity;
import tea4life.order_service.model.constant.TransactionStatus;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 11:25 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "payment_logs")
@SQLDelete(sql = "UPDATE payment_logs SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentLog extends BaseEntity {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(unique = true, nullable = false, name = "gateway_transaction_id")
    String gatewayTransactionId;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    TransactionStatus status;
    String description;
    @Column(nullable = false, name="is_deleted")
    boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    Payment payment;

}
