package com.mp.shop.services;

import com.mp.shop.models.CartItem;
import com.mp.shop.repo.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> findByUser(Long userId) {
        return cartItemRepository.findById_UserId(userId);
    }

    public Long countByUser(Long userId) {
        return cartItemRepository.countCartItemById_UserId(userId);
    }

    public boolean deleteProductByUser(Long productId, Long userId) {
        cartItemRepository.deleteByUserIdAndProductId(productId, userId);
        return true;
    }

    public boolean save(CartItem item) {
        cartItemRepository.save(item);
        return true;
    }
}
