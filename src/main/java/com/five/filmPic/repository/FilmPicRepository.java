package com.five.filmPic.repository;

import com.five.filmPic.model.FilmPic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-7.
 */
public interface FilmPicRepository extends JpaRepository<FilmPic, Integer> {
    FilmPic[] findByFilmIdAndType(int filmId, int type);
}
