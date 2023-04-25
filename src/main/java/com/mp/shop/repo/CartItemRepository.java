package com.mp.shop.repo;

import com.mp.shop.models.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    Long countCartItemByUserId(Long userId);

    Long countCartItemByUserIdAndProductId(Long userId, Long productId);

    @Transactional
    @Modifying
    @Query("delete from CartItem c where c.product.id=:productId and c.user.id=:userId")
    void deleteByUserIdAndProductId(@Param("productId") Long productId, @Param("userId") Long userId);
}
