package tea4life.order_service.dto.request.store;

import jakarta.validation.constraints.NotBlank;

public record AssignStoreEmployeeRequest(
        @NotBlank(message = "keycloakId không được để trống")
        String keycloakId
) {
}
