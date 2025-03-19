package com.gure.cinab.controller.cartItem;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Cart;
import com.gure.cinab.model.User;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.cart.ICartItemService;
import com.gure.cinab.service.cart.ICartService;
import com.gure.cinab.service.user.IUserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
@EnableMethodSecurity()
public class CartItemController implements ICartItemController {

    private final ICartItemService cartItemService;
    private final IUserService userService;
    private final ICartService cartService;


    @Override
    @PostMapping("/item/add")
    @PreAuthorize("hasRole('ADMIN_ROLE') or hasRole('CUSTOMER_ROLE')")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long itemId,
                                                     @RequestParam Integer quantity) {

        Long requestCartId = 0L;
        try {
            User user = userService.getAuthenticatedUser();
            Cart cart = cartService.initializeNewCart(user);


            cartItemService.addItemToCart(cart.getId(), itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Added Item to Cart!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), requestCartId));
        } catch (JwtException jwtE) {
            //if the user is unauthorized (Refer to jwt folder and see classes to understand methods
            return ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(jwtE.getMessage(), null));
        }
    }


    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE') or hasRole('CUSTOMER_ROLE')")
    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {

        try {
            cartItemService.removeItemFromCart(cartId, itemId);

            return ResponseEntity.ok(new ApiResponse("Removed Item Successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE') or hasRole('CUSTOMER_ROLE')")
    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                          @PathVariable Long itemId,
                                                          @RequestParam Integer quantity) {

        try {
            cartItemService.updateItemInCart(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Updated Item Successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
