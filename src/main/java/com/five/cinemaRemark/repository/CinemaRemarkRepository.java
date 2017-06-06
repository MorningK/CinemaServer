package com.five.cinemaRemark.repository;

import com.five.cinemaRemark.model.CinemaRemark;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaRemarkRepository extends JpaRepository<CinemaRemark, Integer> {
    CinemaRemark[] findByUserId(int userId);
    CinemaRemark[] findByCinemaId(int cinemaId);
}
