package tea4life.order_service.dto.request.cart;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record AddCartItemRequest(
        @NotBlank(message = "productId can't be null or empty")
        String productId,
        @NotBlank(message = "productName can't be null or empty")
        String productName,
        String productImageUrl,
        List<CartItemOptionSelectionRequest> selectedOptions,
        @NotNull(message = "unitPrice can't be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "unitPrice must be greater than 0")
        BigDecimal unitPrice,
        @NotNull(message = "quantity can't be null")
        @Min(value = 1, message = "quantity must be at least 1")
        Integer quantity
) {
}
