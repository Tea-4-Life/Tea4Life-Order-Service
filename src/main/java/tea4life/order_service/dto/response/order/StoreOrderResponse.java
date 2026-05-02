package tea4life.order_service.dto.response.order;

import tea4life.order_service.model.constant.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record StoreOrderResponse(
        String id,
        String orderCode,
        String storeId,
        String receiverName,
        String phone,
        String province,
        String ward,
        String detail,
        String keycloakId,
        OrderStatus status,
        String note,
        BigDecimal priceBeforeDiscount,
        BigDecimal finalPrice,
        tea4life.order_service.model.constant.PaymentMethod paymentMethod,
        tea4life.order_service.model.constant.PaymentStatus paymentStatus,
        Instant createdAt,
        List<StoreOrderItemResponse> items
) {
}
