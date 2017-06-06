package com.five.filmRemark.service;

import com.five.user.model.MyMessage;

/**
 * Created by haoye on 17-6-6.
 */
public interface FilmRemarkService {
    MyMessage postFilmRemark(int userId, int filmId, String content);
    Object getFilmRemarkByFilmId(int filmId);
    Object getFilmRemarkByUserId(int userId);
}
