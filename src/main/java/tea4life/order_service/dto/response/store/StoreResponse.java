package tea4life.order_service.dto.response.store;

import java.util.List;

public record StoreResponse(
        String id,
        String name,
        String address,
        Double latitude,
        Double longitude,
        List<StoreEmployeeResponse> employees
) {
}
