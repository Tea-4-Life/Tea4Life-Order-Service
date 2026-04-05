package tea4life.order_service.controller.order;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tea4life.order_service.dto.base.ApiResponse;
import tea4life.order_service.dto.request.order.CheckoutOrderRequest;
import tea4life.order_service.dto.request.order.CreateOrderRequest;
import tea4life.order_service.dto.response.order.OrderResponse;
import tea4life.order_service.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/orders")
public class OrderController {

    OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(orderService.createOrder(request)));
    }

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<OrderResponse>> checkoutMyCart(@RequestBody @Valid CheckoutOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(orderService.checkoutMyCart(request)));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getMyOrders() {
        return ResponseEntity.ok(new ApiResponse<>(orderService.getMyOrders()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getMyOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(orderService.getMyOrderById(id)));
    }
}
