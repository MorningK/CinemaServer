package com.five.film.repository;

import com.five.film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {
    Film findById(int id);
}
