package tea4life.order_service.dto.response.order;

import java.math.BigDecimal;
import java.util.List;

public record OrderItemResponse(
        String productId,
        String productName,
        String productImageUrl,
        List<OrderItemOptionResponse> selectedOptions,
        BigDecimal unitPrice,
        Integer quantity
) {
}
