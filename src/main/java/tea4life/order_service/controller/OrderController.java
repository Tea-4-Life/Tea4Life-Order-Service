package tea4life.order_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tea4life.order_service.dto.base.ApiResponse;

/**
 * @author Le Tran Gia Huy
 * @created 09/03/2026 - 9:41 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.controller.admin
 */

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/public/orders")
public class OrderController {
    @GetMapping("/test")
    public ApiResponse<String> test() {
        return new ApiResponse<>("Hello World");
    }
}
