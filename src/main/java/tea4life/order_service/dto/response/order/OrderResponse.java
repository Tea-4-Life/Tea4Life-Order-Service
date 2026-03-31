package tea4life.order_service.dto.response.order;

import tea4life.order_service.model.constant.OrderStatus;
import tea4life.order_service.model.constant.PaymentMethod;
import tea4life.order_service.model.constant.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        String orderCode,
        String receiverName,
        String phone,
        String detail,
        OrderStatus status,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        Instant createdAt,
        List<OrderItemResponse> items
) {
}
