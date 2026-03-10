package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.constant.PaymentStatus;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 11:13 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(name = "user_id", nullable = false)
    Long userId;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    Order order;
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<PaymentLog> paymentLogs;
}
