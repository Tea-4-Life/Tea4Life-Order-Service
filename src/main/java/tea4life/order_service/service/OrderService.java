package tea4life.order_service.service;

import tea4life.order_service.dto.request.order.CreateOrderRequest;
import tea4life.order_service.dto.request.order.CheckoutOrderRequest;
import tea4life.order_service.dto.response.order.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse checkoutMyCart(CheckoutOrderRequest request);

    List<OrderResponse> getMyOrders();

    OrderResponse getMyOrderById(Long orderId);
}
