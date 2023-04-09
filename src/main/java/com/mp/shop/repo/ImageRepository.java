package com.mp.shop.repo;

import com.mp.shop.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ImageRepository extends JpaRepository<Image, Long> {}
