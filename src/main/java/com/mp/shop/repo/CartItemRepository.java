package com.mp.shop.repo;

import com.mp.shop.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    Long countCartItemByUserId(Long userId);

    void deleteByUserIdAndProductId(Long userId, Long productId);

}
