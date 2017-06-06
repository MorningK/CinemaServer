package com.five.cinema.repository;

import com.five.cinema.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    List<Cinema> findByCitycode(int citycode);
}
