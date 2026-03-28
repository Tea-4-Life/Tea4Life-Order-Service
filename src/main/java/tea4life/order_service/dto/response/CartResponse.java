package tea4life.order_service.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
        String id,
        String keycloakId,
        List<CartItemResponse> items,
        Integer totalItems,
        BigDecimal totalAmount
) {
}
