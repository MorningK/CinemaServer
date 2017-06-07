package com.five.filmPic.service;

import com.five.film.service.FilmService;
import com.five.filmPic.dao.FilmPicDao;
import com.five.filmPic.model.FilmPic;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by haoye on 17-6-7.
 */
@Service
@Transactional
public class FilmPicServiceImpl implements FilmPicService {

    @Autowired
    private FilmPicDao filmPicDao;
    @Autowired
    private FilmService filmService;

    @Override
    public Object getFilmCover(int filmId) {
        if (filmService.findById(filmId) == null) {
            return new MyMessage(0, "电影不存在");
        }

        FilmPic[] filmPics = filmPicDao.findByFilmIdAndType(filmId, FilmPic.COVER);
        String[] result = new String[filmPics.length];
        for (int i = 0; i < filmPics.length; i++) {
            result[i] = filmPics[i].getPath();
        }
        return result;
    }

    @Override
    public Object getFilmStill(int filmId) {
        if (filmService.findById(filmId) == null) {
            return new MyMessage(0, "电影不存在");
        }

        FilmPic[] filmPics = filmPicDao.findByFilmIdAndType(filmId, FilmPic.STILL);
        String[] result = new String[filmPics.length];
        for (int i = 0; i < filmPics.length; i++) {
            result[i] = filmPics[i].getPath();
        }
        return result;
    }
}
