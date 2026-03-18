package tea4life.order_service.dto.response;

/**
 * @author Le Tran Gia Huy
 * @created 18/03/2026 - 12:12 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.dto.response
 */
public record VoucherResponse(
        String id,
        double discountPercentage,
        String minOrderAmount,
        String maxDiscountAmount,
        String description,
        String imgUrl
) {
}
