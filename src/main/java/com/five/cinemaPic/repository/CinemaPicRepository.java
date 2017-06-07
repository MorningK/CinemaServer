package com.five.cinemaPic.repository;

import com.five.cinemaPic.model.CinemaPic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaPicRepository extends JpaRepository<CinemaPic, Integer> {
    List<CinemaPic> findByCinemaIdAndType(int cinemaId, int type);
}
