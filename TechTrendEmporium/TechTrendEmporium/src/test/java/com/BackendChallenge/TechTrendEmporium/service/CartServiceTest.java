package com.BackendChallenge.TechTrendEmporium.service;
import com.BackendChallenge.TechTrendEmporium.entity.*;
import com.BackendChallenge.TechTrendEmporium.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private CartService cartService;

//    @Test
//    public void existsCartTest_Success() {
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
//        assertNotNull(cartService.existsCart(1L, "OPEN"));
//    }

    @Test
    public void addProductToCartTest_CartDoesNotExist() {
        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
        assertFalse(cartService.addProductToCart(1L, 1L, 1));
    }

//    @Test
//    public void addProductToCartTest_ProductExistsInCart_QuantityLessThanInventory() {
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
//        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
//        when(cartProductRepository.findByCartIdAndProductId(any(), any())).thenReturn(new CartProduct());
//        assertTrue(cartService.addProductToCart(1L, 1L, 1));
//    }

//    @Test
//    public void addProductToCartTest_ProductExistsInCart_QuantityMoreThanInventory() {
//        // You need to set up a Product with an Inventory that has less available than the quantity being added
//        Product product = new Product();
//        Product.Inventory inventory = new Product.Inventory();
//        inventory.setAvailable(0);
//        product.setInventory(inventory);
//
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
//        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//        when(cartProductRepository.findByCartIdAndProductId(any(), any())).thenReturn(new CartProduct());
//        assertFalse(cartService.addProductToCart(1L, 1L, 2));
//    }
//
//    @Test
//    public void addProductToCartTest_ProductDoesNotExistInCart_QuantityLessThanInventory() {
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
//        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
//        when(cartProductRepository.findByCartIdAndProductId(any(), any())).thenReturn(null);
//        assertTrue(cartService.addProductToCart(1L, 1L, 1));
//    }

//    @Test
//    public void deleteProductFromCartTest_Success() {
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
//        when(cartProductRepository.findByCartIdAndProductId(any(), any())).thenReturn(new CartProduct());
//        assertTrue(cartService.deleteProductFromCart(1L, 1L, 1));
//    }

    @Test
    public void deleteProductFromCartTest_Failure() {
        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
        assertFalse(cartService.deleteProductFromCart(1L, 1L, 1));
    }

//    @Test
//    public void getCartByUserTest_Success() {
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
//        assertNull(cartService.getCartByUser(1L));
//    }

    @Test
    public void getCartByUserTest_NotFound() {
        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
        assertNull(cartService.getCartByUser(1L));
    }

//    @Test
//    public void checkoutTest_Success() {
//        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
//        when(cartProductRepository.findByCartId(any())).thenReturn(new ArrayList<>());
//        assertNotNull(cartService.checkout(1L));
//    }

    @Test
    public void checkoutTest_Failure() {
        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
        assertEquals("Checkout failed, cart not found.", cartService.checkout(1L).getMessage());
    }

    @Test
    public void applyCouponTest_Success() {
        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.of(new Cart()));
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        when(couponRepository.findByName(any())).thenReturn(Optional.of(new Coupon()));
        assertTrue(cartService.applyCoupon(1L, "testCoupon"));
    }

    @Test
    public void applyCouponTest_Failure() {
        when(cartRepository.findByUserIdAndStatus(any(), any())).thenReturn(Optional.empty());
        assertFalse(cartService.applyCoupon(1L, "testCoupon"));
    }
}