package tea4life.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tea4life.order_service.model.constant.OrderStatus;
import tea4life.order_service.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStoreIdOrderByCreatedAtDesc(Long storeId);

    List<Order> findByStoreIdAndStatusOrderByCreatedAtDesc(Long storeId, OrderStatus status);

    Optional<Order> findByIdAndStoreId(Long id, Long storeId);

    List<Order> findByKeycloakIdOrderByCreatedAtDesc(String keycloakId);

    Optional<Order> findByIdAndKeycloakId(Long id, String keycloakId);
}
