package com.five.cinema.repository;

import com.five.cinema.model.Cinema;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@CacheConfig(cacheNames = "cinema")
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    @Cacheable
    List<Cinema> findByCitycode(int citycode);
    @Cacheable
    Cinema findOne(Integer id);

}
