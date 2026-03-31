package tea4life.order_service.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderItemOptionRequest(
        @NotBlank(message = "productOptionId không được để trống")
        String productOptionId,

        @NotBlank(message = "productOptionName không được để trống")
        String productOptionName,

        @NotBlank(message = "productOptionValueId không được để trống")
        String productOptionValueId,

        @NotBlank(message = "productOptionValueName không được để trống")
        String productOptionValueName,

        @NotNull(message = "extraPrice không được để trống")
        BigDecimal extraPrice
) {
}
