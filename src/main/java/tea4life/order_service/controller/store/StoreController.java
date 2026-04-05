package tea4life.order_service.controller.store;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tea4life.order_service.dto.base.ApiResponse;
import tea4life.order_service.dto.response.store.StoreResponse;
import tea4life.order_service.service.StoreService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/stores")
public class StoreController {

    StoreService storeService;

    // ====================================
    // USER STORE
    // ====================================

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<StoreResponse>> getMyStore() {
        return ResponseEntity.ok(new ApiResponse<>(storeService.findMyStore()));
    }
}
