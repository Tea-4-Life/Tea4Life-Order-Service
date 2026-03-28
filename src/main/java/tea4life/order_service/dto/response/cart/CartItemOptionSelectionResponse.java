package tea4life.order_service.dto.response.cart;

import java.math.BigDecimal;

public record CartItemOptionSelectionResponse(
        String productOptionId,
        String productOptionName,
        String productOptionValueId,
        String productOptionValueName,
        BigDecimal extraPrice
) {
}
