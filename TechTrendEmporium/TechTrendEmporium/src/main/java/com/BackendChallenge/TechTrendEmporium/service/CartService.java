package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Cart;
import com.BackendChallenge.TechTrendEmporium.entity.CartProduct;
import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.*;
import com.BackendChallenge.TechTrendEmporium.service.Response.CartResponse;
import com.BackendChallenge.TechTrendEmporium.service.Response.ProductQuantity;
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

    public boolean addProductToCart(Long userId, Long productId, int quantity) {
        Cart cart = existsCart(userId);
        Optional<Product> product = productRepository.findById(productId);
        Optional<CartProduct> cartProductV = Optional.ofNullable(cartProductRepository.findByCartIdAndProductId(cart.getId(), productId));
        CartProduct cartProduct = new CartProduct();
        if (product.isPresent()) {
            cartProduct.setCart(cart);
            cartProduct.setProduct(product.get());
            if (cartProductV.isPresent()) {
                cartProduct.setQuantity(cartProductV.get().getQuantity() + quantity);
                if (cartProduct.getQuantity() > product.get().getInventory().getAvailable()) {
                    return false;
                }
                cartProductRepository.updateQuantity(cartProduct.getQuantity(), cartProductV.get().getId());
            } else {
                cartProduct.setQuantity(quantity);
                cartProductRepository.save(cartProduct);
            }
            return true;
        }
        return false;
    }

    public boolean deleteProductFromCart(Long userId, Long productId, int quantity) {
        Cart cart = existsCart(userId);
        Optional<CartProduct> cartProduct = Optional.ofNullable(cartProductRepository.findByCartIdAndProductId(cart.getId(), productId));
        if (cartProduct.isPresent()) {
            if (cartProduct.get().getQuantity() <= quantity) {
                cartProductRepository.delete(cartProduct.get());
            } else {
                cartProduct.get().setQuantity(cartProduct.get().getQuantity() - quantity);
                cartProductRepository.updateQuantity(cartProduct.get().getQuantity(), cartProduct.get().getId());
            }
            return true;
        }
        return false;
    }

    public CartResponse getCartByUser(Long userId) {
        Cart cart = existsCart(userId);
        CartResponse response = new CartResponse();
        List<CartProduct> cartProducts = cartProductRepository.findByCartId(cart.getId());
        List<ProductQuantity> products = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts) {
            ProductQuantity productQuantity = new ProductQuantity(cartProduct.getProduct().getId(), cartProduct.getQuantity());
            products.add(productQuantity);
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

    public boolean checkout(Long userId) {
        return true;
    }

    public boolean applyCoupon(Long userId) {
        return true;
    }
}
