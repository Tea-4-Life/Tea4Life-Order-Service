package tea4life.order_service.dto.request.cart;

import java.math.BigDecimal;

public record CartItemOptionSelectionRequest(
        String productOptionId,
        String productOptionName,
        String productOptionValueId,
        String productOptionValueName,
        BigDecimal extraPrice
) {
}
