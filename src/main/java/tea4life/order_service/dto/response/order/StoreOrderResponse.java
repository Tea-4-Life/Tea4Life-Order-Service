package tea4life.order_service.dto.response.order;

import tea4life.order_service.model.constant.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record StoreOrderResponse(
        String id,
        String storeId,
        String keycloakId,
        OrderStatus status,
        String note,
        BigDecimal priceBeforeDiscount,
        BigDecimal finalPrice,
        Instant createdAt,
        List<StoreOrderItemResponse> items
) {
}
