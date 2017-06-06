package com.five.order.repository;

import com.five.order.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */
public interface OrderRepository extends JpaRepository<Reservation, Integer> {
}
