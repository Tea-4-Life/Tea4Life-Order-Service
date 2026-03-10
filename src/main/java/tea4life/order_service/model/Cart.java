package tea4life.order_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tea4life.order_service.config.database.SnowflakeGenerated;

import java.util.Set;

/**
 * @author Le Tran Gia Huy
 * @created 10/03/2026 - 10:37 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.model
 */

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @SnowflakeGenerated
    @Id
    Long id;

    @Column(unique = true, nullable = false, name = "user_id")
    Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<CartItem> cartItems;

}
