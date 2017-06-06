package com.five.filmRemark.repository;

import com.five.filmRemark.model.FilmRemark;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */
public interface FilmRemarkRepository extends JpaRepository<FilmRemark, Integer> {
    FilmRemark[] findByUserId(int userId);
    FilmRemark[] findByFilmId(int filmId);
}
