package tea4life.order_service.dto.response.order;

import java.math.BigDecimal;

public record OrderItemOptionResponse(
        String productOptionId,
        String productOptionName,
        String productOptionValueId,
        String productOptionValueName,
        BigDecimal extraPrice
) {
}
