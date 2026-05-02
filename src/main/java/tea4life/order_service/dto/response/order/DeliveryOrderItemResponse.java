package tea4life.order_service.dto.response.order;

import java.math.BigDecimal;

public record DeliveryOrderItemResponse(
        String id,
        String productId,
        String productName,
        String productImageUrl,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subTotal
) {
}
