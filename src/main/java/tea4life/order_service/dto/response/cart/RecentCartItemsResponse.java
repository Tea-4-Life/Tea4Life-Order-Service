package tea4life.order_service.dto.response.cart;

import java.util.List;

public record RecentCartItemsResponse(
        List<CartItemResponse> items,
        Integer totalItems
) {
}
