package com.five.filmPic.service;

import com.five.film.service.FilmService;
import com.five.filmPic.dao.FilmPicDao;
import com.five.filmPic.model.FilmPic;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        List<FilmPic> filmPics = filmPicDao.findByFilmIdAndType(filmId, FilmPic.COVER);
        String[] result = new String[filmPics.size()];
        for (int i = 0; i < filmPics.size(); i++) {
            result[i] = filmPics.get(i).getPath();
        }
        if (result.length == 0) return new MyMessage(0,"无照片");
        return new MyMessage(1, result);
    }

    @Override
    public Object getFilmStill(int filmId) {
        if (filmService.findById(filmId) == null) {
            return new MyMessage(0, "电影不存在");
        }

        List<FilmPic> filmPics = filmPicDao.findByFilmIdAndType(filmId, FilmPic.STILL);
        String[] result = new String[filmPics.size()];
        for (int i = 0; i < filmPics.size(); i++) {
            result[i] = filmPics.get(i).getPath();
        }
        if (result.length == 0) return new MyMessage(0,"无照片");
        return new MyMessage(1, result);
    }
}
