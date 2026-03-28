package tea4life.order_service.dto.response.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartItemResponse(
        String id,
        String productId,
        String productName,
        String productImageUrl,
        List<CartItemOptionSelectionResponse> selectedOptions,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal subTotal
) {
}
