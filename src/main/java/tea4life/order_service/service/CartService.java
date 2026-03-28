package tea4life.order_service.service;

import tea4life.order_service.dto.request.AddCartItemRequest;
import tea4life.order_service.dto.request.UpdateCartItemRequest;
import tea4life.order_service.dto.response.CartResponse;

public interface CartService {

    CartResponse getMyCart();

    CartResponse addItemToMyCart(AddCartItemRequest request);

    CartResponse updateMyCartItem(Long cartItemId, UpdateCartItemRequest request);

    void removeMyCartItem(Long cartItemId);

    void clearMyCart();
}
