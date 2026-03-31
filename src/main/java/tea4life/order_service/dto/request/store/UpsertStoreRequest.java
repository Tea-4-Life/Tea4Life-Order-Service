package tea4life.order_service.dto.request.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpsertStoreRequest(
        @NotBlank(message = "Tên cửa hàng không được để trống")
        String name,

        @NotBlank(message = "Địa chỉ không được để trống")
        String address,

        @NotNull(message = "Latitude không được để trống")
        Double latitude,

        @NotNull(message = "Longitude không được để trống")
        Double longitude
) {
}
