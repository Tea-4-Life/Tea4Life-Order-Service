package tea4life.order_service.dto.response.order;

import java.math.BigDecimal;

public record StoreOrderItemResponse(
        String id,
        String productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subTotal
) {
}
