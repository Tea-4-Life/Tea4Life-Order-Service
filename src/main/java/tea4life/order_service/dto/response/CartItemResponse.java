package tea4life.order_service.dto.response;

import java.math.BigDecimal;

public record CartItemResponse(
        String id,
        Long productId,
        String productName,
        String productImageUrl,
        String productVariant,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal subTotal
) {
}
