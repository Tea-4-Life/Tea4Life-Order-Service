package tea4life.order_service.controller.order;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tea4life.order_service.dto.base.ApiResponse;
import tea4life.order_service.dto.response.order.StoreOrderResponse;
import tea4life.order_service.model.constant.OrderStatus;
import tea4life.order_service.service.StoreOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/stores/me/orders")
public class StoreOrderController {

    StoreOrderService storeOrderService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StoreOrderResponse>>> findMyStoreOrders(
            @RequestParam(value = "status", required = false) OrderStatus status
    ) {
        return ResponseEntity.ok(new ApiResponse<>(storeOrderService.findMyStoreOrders(status)));
    }

    @PostMapping("/{orderId}/accept")
    public ResponseEntity<ApiResponse<StoreOrderResponse>> acceptOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(new ApiResponse<>(storeOrderService.acceptOrder(orderId)));
    }

    @PostMapping("/{orderId}/ready-for-delivery")
    public ResponseEntity<ApiResponse<StoreOrderResponse>> markOrderReadyForDelivery(@PathVariable Long orderId) {
        return ResponseEntity.ok(new ApiResponse<>(storeOrderService.markOrderReadyForDelivery(orderId)));
    }
}
