package tea4life.order_service.service;

import tea4life.order_service.model.constant.OrderStatus;
import tea4life.order_service.dto.response.order.StoreOrderResponse;

import java.util.List;

public interface StoreOrderService {

    List<StoreOrderResponse> findMyStoreOrders(OrderStatus status);

    StoreOrderResponse acceptOrder(Long orderId);

    StoreOrderResponse markOrderReadyForDelivery(Long orderId);
}
