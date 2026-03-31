package tea4life.order_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tea4life.order_service.dto.response.order.StoreOrderItemResponse;
import tea4life.order_service.dto.response.order.StoreOrderResponse;
import tea4life.order_service.model.constant.OrderStatus;
import tea4life.order_service.model.order.Order;
import tea4life.order_service.model.order.OrderItem;
import tea4life.order_service.model.store.StoreEmployee;
import tea4life.order_service.repository.OrderRepository;
import tea4life.order_service.repository.StoreEmployeeRepository;
import tea4life.order_service.service.StoreOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class StoreOrderServiceImpl implements StoreOrderService {

    // Repository
    OrderRepository orderRepository;
    StoreEmployeeRepository storeEmployeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StoreOrderResponse> findMyStoreOrders(OrderStatus status) {
        Long storeId = resolveCurrentStoreId();
        List<Order> orders = (status == null)
                ? orderRepository.findByStoreIdOrderByCreatedAtDesc(storeId)
                : orderRepository.findByStoreIdAndStatusOrderByCreatedAtDesc(storeId, status);

        return orders.stream()
                .map(this::toStoreOrderResponse)
                .toList();
    }

    @Override
    public StoreOrderResponse acceptOrder(Long orderId) {
        Order order = findOrderInCurrentStore(orderId);
        ensureStatus(order, OrderStatus.PENDING, "Chỉ có thể nhận đơn đang ở trạng thái PENDING");
        order.setStatus(OrderStatus.PREPARING);
        return toStoreOrderResponse(orderRepository.save(order));
    }

    @Override
    public StoreOrderResponse markOrderReadyForDelivery(Long orderId) {
        Order order = findOrderInCurrentStore(orderId);
        ensureStatus(order, OrderStatus.PREPARING, "Chỉ có thể xác nhận xong đơn đang ở trạng thái PREPARING");
        order.setStatus(OrderStatus.READY_FOR_DELIVERY);
        return toStoreOrderResponse(orderRepository.save(order));
    }

    // =================================================
    // Lookup
    // =================================================

    private Order findOrderInCurrentStore(Long orderId) {
        Long storeId = resolveCurrentStoreId();
        return orderRepository.findByIdAndStoreId(orderId, storeId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy đơn hàng thuộc chi nhánh hiện tại"
                ));
    }

    private Long resolveCurrentStoreId() {
        String keycloakId = resolveCurrentKeycloakId();
        List<StoreEmployee> storeEmployees = storeEmployeeRepository.findByKeycloakIdOrderByCreatedAtAsc(keycloakId);

        if (storeEmployees.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bạn chưa được gán vào chi nhánh nào");
        }
        if (storeEmployees.size() > 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Bạn đang được gán nhiều chi nhánh");
        }

        return storeEmployees.get(0).getStore().getId();
    }

    // =================================================
    // Validation
    // =================================================

    private String resolveCurrentKeycloakId() {
        tea4life.order_service.context.UserContext context = tea4life.order_service.context.UserContext.get();
        if (context == null || context.getKeycloakId() == null || context.getKeycloakId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Không xác định được người dùng hiện tại");
        }
        return context.getKeycloakId().trim();
    }

    private void ensureStatus(Order order, OrderStatus expectedStatus, String message) {
        if (order.getStatus() != expectedStatus) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
    }

    // =================================================
    // Mapping
    // =================================================

    private StoreOrderResponse toStoreOrderResponse(Order order) {
        List<StoreOrderItemResponse> items = order.getOrderItems() == null
                ? List.of()
                : order.getOrderItems().stream()
                .map(this::toStoreOrderItemResponse)
                .toList();

        return new StoreOrderResponse(
                order.getId() == null ? null : order.getId().toString(),
                order.getStore() == null || order.getStore().getId() == null ? null : order.getStore().getId().toString(),
                order.getKeycloakId(),
                order.getStatus(),
                order.getNote(),
                order.getPriceBeforeDiscount(),
                order.getFinalPrice(),
                order.getCreatedAt(),
                items
        );
    }

    private StoreOrderItemResponse toStoreOrderItemResponse(OrderItem item) {
        return new StoreOrderItemResponse(
                item.getId() == null ? null : item.getId().toString(),
                item.getProductId() == null ? null : item.getProductId().toString(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSubTotal()
        );
    }
}
