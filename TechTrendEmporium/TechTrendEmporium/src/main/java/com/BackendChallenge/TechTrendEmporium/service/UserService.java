package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.User;
import com.BackendChallenge.TechTrendEmporium.repository.UserRepository;
import com.BackendChallenge.TechTrendEmporium.entity.Cart;
import com.BackendChallenge.TechTrendEmporium.entity.CartProduct;
import com.BackendChallenge.TechTrendEmporium.entity.Wishlist;
import com.BackendChallenge.TechTrendEmporium.entity.WishlistProduct;
import com.BackendChallenge.TechTrendEmporium.entity.Sale;
import com.BackendChallenge.TechTrendEmporium.entity.Review;
import com.BackendChallenge.TechTrendEmporium.repository.CartRepository;
import com.BackendChallenge.TechTrendEmporium.repository.CartProductRepository;
import com.BackendChallenge.TechTrendEmporium.repository.WishlistRepository;
import com.BackendChallenge.TechTrendEmporium.repository.WishlistProductRepository;
import com.BackendChallenge.TechTrendEmporium.repository.SaleRepository;
import com.BackendChallenge.TechTrendEmporium.repository.ReviewRepository;
import com.BackendChallenge.TechTrendEmporium.service.Response.GetUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private WishlistProductRepository wishlistProductRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    public Boolean deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            List<Cart> carts = cartRepository.findAllByUserId(userId);
            for (Cart cart : carts) {
                List<CartProduct> cartProducts = cartProductRepository.findByCartId(cart.getId());
                cartProductRepository.deleteAll(cartProducts);
                cartRepository.delete(cart);
            }

            List<Wishlist> wishlists = wishlistRepository.findAllByUserId(userId);
            for (Wishlist wishlist : wishlists) {
                List<WishlistProduct> wishlistProducts = wishlistProductRepository.findByWishlistId(wishlist.getId());
                wishlistProductRepository.deleteAll(wishlistProducts);
                List<Sale> sales = saleRepository.findByCartUserId(userId);
                saleRepository.deleteAll(sales);
                wishlistRepository.delete(wishlist);
            }

            List<Review> reviews = reviewRepository.findByUserId(userId);
            reviewRepository.deleteAll(reviews);

            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public Boolean updateUser(Long userId, String name, String email, String password) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<User> userByEmail = userRepository.findByEmail(email);
            Optional<User> userByName = userRepository.findByUsername(name);
            if ((userByEmail.isPresent() || userByName.isPresent())){
                return false;
            }
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<GetUsersResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<GetUsersResponse> response = new ArrayList<>();
        for (User user : users) {
            GetUsersResponse getUsersResponse = GetUsersResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
            response.add(getUsersResponse);
        }
        return response;
    }
}
