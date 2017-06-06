package com.five.filmSession.service;

import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmSessionService {
    MyMessage getFilmSession(int filmId, int cinemaId, String time);
}
