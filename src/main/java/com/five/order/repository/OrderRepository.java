package com.five.order.repository;

import com.five.order.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by haoye on 17-6-6.
 */
public interface OrderRepository extends JpaRepository<Reservation, Integer> {

    @Modifying
    @Query(value="update Reservation r set r.status=:newStatus where r.id=:rid")
    int findById(@Param("rid") int id, @Param("newStatus") int newStatus);
}
