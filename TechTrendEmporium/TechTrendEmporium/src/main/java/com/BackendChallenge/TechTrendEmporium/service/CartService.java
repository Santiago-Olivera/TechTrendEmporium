package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Cart;
import com.BackendChallenge.TechTrendEmporium.entity.CartProduct;
import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.*;
import com.BackendChallenge.TechTrendEmporium.service.Response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    public boolean addProductToCart(Long userId, Long productId) {
        Cart cart = existsCart(userId);
        Optional<Product> product = productRepository.findById(productId);
        Optional<CartProduct> cartProductV = Optional.ofNullable(cartProductRepository.findByCartIdAndProductId(cart.getId(), productId));
        CartProduct cartProduct = new CartProduct();
        if (product.isPresent() && cartProductV.isEmpty()) {
            cartProduct.setCart(cart);
            cartProduct.setProduct(product.get());
            cartProductRepository.save(cartProduct);
            return true;
        }
        return false;
    }

    public boolean deleteProductFromCart(Long userId, Long productId) {
        Cart cart = existsCart(userId);
        Optional<CartProduct> cartProduct = Optional.ofNullable(cartProductRepository.findByCartIdAndProductId(cart.getId(), productId));
        if (cartProduct.isPresent()) {
            cartProductRepository.delete(cartProduct.get());
            return true;
        }
        return false;
    }

    public CartResponse getCartByUser(Long userId) {
        Cart cart = existsCart(userId);
        CartResponse response = new CartResponse();
        List<CartProduct> cartProducts = cartProductRepository.findByCartId(cart.getId());
        List<Long> products = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts) {
            products.add(cartProduct.getProduct().getId());
        }
        response.setUser_id(userId);
        response.setProducts(products);
        return response;
    }

    public Cart existsCart(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()) {
            return cart.get();
        }
        else {
            Cart newCart = new Cart();
            newCart.setUser(userRepository.findById(userId).orElse(null));
            cartRepository.save(newCart);
            return newCart;
        }
    }
}
