package tea4life.order_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tea4life.order_service.context.UserContext;
import tea4life.order_service.dto.request.AddCartItemRequest;
import tea4life.order_service.dto.request.UpdateCartItemRequest;
import tea4life.order_service.dto.response.CartItemResponse;
import tea4life.order_service.dto.response.CartResponse;
import tea4life.order_service.model.cart.Cart;
import tea4life.order_service.model.cart.CartItem;
import tea4life.order_service.repository.CartItemRepository;
import tea4life.order_service.repository.CartRepository;
import tea4life.order_service.service.CartService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;
    CartItemRepository cartItemRepository;

    @Override
    @Transactional(readOnly = true)
    public CartResponse getMyCart() {
        Cart cart = getOrCreateCart(resolveCurrentUserId());
        return toCartResponse(cart);
    }

    @Override
    public CartResponse addItemToMyCart(AddCartItemRequest request) {
        Cart cart = getOrCreateCart(resolveCurrentUserId());

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        applyAddRequestToCartItem(cartItem, request);
        cartItemRepository.save(cartItem);

        return toCartResponse(cartRepository.findById(cart.getId()).orElse(cart));
    }

    @Override
    public CartResponse updateMyCartItem(Long cartItemId, UpdateCartItemRequest request) {
        Cart cart = getOrCreateCart(resolveCurrentUserId());
        CartItem cartItem = findCartItem(cart.getId(), cartItemId);

        cartItem.setQuantity(request.quantity());
        cartItem.setSubTotal(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(request.quantity())));
        cartItemRepository.save(cartItem);

        return toCartResponse(cartRepository.findById(cart.getId()).orElse(cart));
    }

    @Override
    public void removeMyCartItem(Long cartItemId) {
        Cart cart = getOrCreateCart(resolveCurrentUserId());
        CartItem cartItem = findCartItem(cart.getId(), cartItemId);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearMyCart() {
        Cart cart = getOrCreateCart(resolveCurrentUserId());
        if (cart.getCartItems() != null) {
            cart.getCartItems().clear();
        }
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(buildEmptyCart(userId)));
    }

    private Cart buildEmptyCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCartItems(new LinkedHashSet<>());
        return cart;
    }

    private CartItem findCartItem(Long cartId, Long cartItemId) {
        return cartItemRepository.findByIdAndCartId(cartItemId, cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy Cart Item với ID: " + cartItemId));
    }

    private void applyAddRequestToCartItem(CartItem cartItem, AddCartItemRequest request) {
        cartItem.setProductId(request.productId());
        cartItem.setProductName(request.productName().trim());
        cartItem.setProductImageUrl(trimToNull(request.productImageUrl()));
        cartItem.setProductVariant(trimToNull(request.productVariant()));
        cartItem.setUnitPrice(request.unitPrice());
        cartItem.setQuantity(request.quantity());
        cartItem.setSubTotal(request.unitPrice().multiply(BigDecimal.valueOf(request.quantity())));
    }

    private CartResponse toCartResponse(Cart cart) {
        List<CartItemResponse> itemResponses = cart.getCartItems() == null
                ? List.of()
                : cart.getCartItems().stream()
                .sorted(Comparator.comparing(CartItem::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(CartItem::getId, Comparator.nullsLast(Long::compareTo)))
                .map(this::toCartItemResponse)
                .toList();

        int totalItems = itemResponses.stream()
                .map(CartItemResponse::quantity)
                .reduce(0, Integer::sum);

        BigDecimal totalAmount = itemResponses.stream()
                .map(CartItemResponse::subTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(
                cart.getId() == null ? null : cart.getId().toString(),
                cart.getUserId(),
                itemResponses,
                totalItems,
                totalAmount
        );
    }

    private CartItemResponse toCartItemResponse(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId() == null ? null : cartItem.getId().toString(),
                cartItem.getProductId(),
                cartItem.getProductName(),
                cartItem.getProductImageUrl(),
                cartItem.getProductVariant(),
                cartItem.getUnitPrice(),
                cartItem.getQuantity(),
                cartItem.getSubTotal()
        );
    }

    private Long resolveCurrentUserId() {
        UserContext context = UserContext.get();
        if (context == null || context.getKeycloakId() == null || context.getKeycloakId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Không xác định được người dùng hiện tại");
        }

        try {
            return Long.parseLong(context.getKeycloakId().trim());
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "X-User-KeycloakId không phải user id hợp lệ", ex);
        }
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
