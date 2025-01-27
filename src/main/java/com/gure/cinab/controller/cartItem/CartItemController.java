package com.gure.cinab.controller.cartItem;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.cart.ICartItemService;
import com.gure.cinab.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController implements ICartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;


    @Override
    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long cartId,
                                                     @RequestParam Long itemId,
                                                     @RequestParam Integer quantity) {

        try {
        if(cartId == null){
           cartId = cartService.initializeNewCart();
        }
            cartItemService.addItemToCart(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Adding Item Successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), cartId));
        }
    }


    @Override
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
