package tea4life.order_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * @author Le Tran Gia Huy
 * @created 18/03/2026 - 11:48 AM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.dto.request
 */
public record CreateVoucherRequest(
        @Min(0)
        double discountPercentage,
        @NotNull(message = "minOrderAmount have to be a double-type number")
        BigDecimal minOrderAmount,
        @NotNull(message = "maxDiscountAmount have to be a double-type number")
        BigDecimal maxDiscountAmount,
        @NotBlank(message = "description can't be null or empty")
        String description,
        @NotBlank(message = "imgKey can't be null or empty")
        String imgKey

) {
}
