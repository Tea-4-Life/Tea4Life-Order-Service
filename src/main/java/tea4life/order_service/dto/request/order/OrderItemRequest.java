package tea4life.order_service.dto.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderItemRequest(
        @NotBlank(message = "productId không được để trống")
        String productId,

        @NotBlank(message = "productName không được để trống")
        String productName,

        String productImageUrl,

        @Valid
        List<OrderItemOptionRequest> selectedOptions,

        @NotNull(message = "unitPrice không được để trống")
        BigDecimal unitPrice,

        @NotNull(message = "quantity không được để trống")
        @Min(value = 1, message = "quantity phải lớn hơn 0")
        Integer quantity
) {
}
