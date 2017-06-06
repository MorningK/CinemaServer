package com.five.hallSitting.repository;

import com.five.hallSitting.model.HallSitting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */
public interface HallSittingRepository extends JpaRepository<HallSitting, Integer> {
}
