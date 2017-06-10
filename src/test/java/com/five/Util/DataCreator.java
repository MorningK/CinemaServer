package com.five.Util;

import com.five.cinema.model.Cinema;
import com.five.cinemaPic.model.CinemaPic;
import com.five.cinemaRemark.model.CinemaRemark;
import com.five.film.Util.FilmUtil;
import com.five.film.model.Film;
import com.five.filmPic.model.FilmPic;
import com.five.filmRemark.model.FilmRemark;
import com.five.filmSession.model.FilmSession;
import com.five.hallSitting.model.HallSitting;
import com.five.hallSitting.model.OneSit;
import com.five.hallSitting.model.Sits;
import com.five.hallSitting.utils.SitUtil;
import com.five.order.model.Reservation;
import com.five.payment.model.Wallet;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.utils.CodeUtil;
import net.sf.json.JSONArray;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by msi on 2017/6/8.
 */
public class DataCreator {

    private static Random random = new Random();

    public static List<Cinema> prepareCinema(int n) {
        List<Cinema> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = "cinema" + Integer.toString(i);
            String address = "address" + Integer.toString(i);
            String phone = Integer.toString(i);
            double lo = random.nextDouble();
            double la = random.nextDouble();
            int cityCode = random.nextInt(n);
            Cinema cinema = new Cinema(name, address,phone, lo, la, cityCode);
            ans.add(cinema);
        }
        return ans;
    }

    public static List<Cinema> prepareCinemaWithSameCity(int n, int city_code, double centerlo, double centerla) {
        List<Cinema> ans = new ArrayList<>();
//        double centerlo = random.nextDouble()*5;
//        double centerla = random.nextDouble()*5;
        for (int i = 0; i < n; i++) {
            String name = "cinema" + Integer.toString(i);
            String address = "address" + Integer.toString(i);
            String phone = Integer.toString(i);
            double lo = centerlo + 1.5*i;
            double la = centerla + 1.5*i;
            int cityCode = city_code;
            Cinema cinema = new Cinema(name, address,phone, lo, la, cityCode);
            ans.add(cinema);
        }
        return ans;
    }

    public static List<CinemaPic> prepareCinemaPic(int n, int cinemaIdMax) {
        List<CinemaPic> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String path = "path" + Integer.toString(i);
            int type = random.nextDouble()>0.5?1:0;
            int cinemaId = random.nextInt(cinemaIdMax)+1;
            CinemaPic cinemaPic = new CinemaPic(path, type, cinemaId);
            ans.add(cinemaPic);
        }
        return ans;
    }

    public static List<CinemaRemark> prepareCinemaRemark(int n,int userIdMax, int cinemaIdMax) {
        List<CinemaRemark> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int userId = random.nextInt(userIdMax)+1;
            int cinemaId = random.nextInt(cinemaIdMax)+1;
            String content = "content" + Integer.toString(i);
            CinemaRemark cinemaRemark = new CinemaRemark(userId, cinemaId, content);
            ans.add(cinemaRemark);
        }
        return ans;
    }

    public static List<Film> prepareFilm(int n) {
        List<Film> ans = new ArrayList<>();
        Date d = new Date();
        for (int i = 0; i < n; i++) {
            String name = "film" + Integer.toString(i);
            String actor = "actor" + Integer.toString(i);
            String category = "category" + Integer.toString(i);
            String director = "director" + Integer.toString(i);
            String language = "language" + Integer.toString(i);
            long a = random.nextLong() > 0?random.nextLong()%31536000000L:(-random.nextLong())%31536000000L;
            Timestamp publishTime = new Timestamp(d.getTime() + a);
            double lastTime = random.nextDouble()*1.5*3600;
            String nation = "nation" + Integer.toString(i);
            String summary = "summary" + Integer.toString(i);
            double score = random.nextDouble() * 5;
            Film film = new Film( name, summary, score, category, nation, publishTime, lastTime, actor, director, language);
            ans.add(film);
        }
        return ans;
    }

    public static List<FilmPic> prepareFilmPic(int n, int filmIdMax) {
        List<FilmPic> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String path = "path" + Integer.toString(i);
            int type = random.nextDouble()>0.5?FilmPic.COVER:FilmPic.STILL;
            int filmId = random.nextInt(filmIdMax)+1;
            FilmPic filmPic = new FilmPic(path, type, filmId);
            ans.add(filmPic);
        }
        return ans;
    }

    public static List<FilmRemark> prepareFilmRemark(int n, int userIdMax, int filmIdMax) {
        List<FilmRemark> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int userId = random.nextInt(userIdMax)+1;
            int filmId = random.nextInt(filmIdMax)+1;
            String content = "content" + Integer.toString(i);
            FilmRemark filmRemark = new FilmRemark(userId, filmId, content);
            ans.add(filmRemark);
        }
        return ans;
    }

    public static List<FilmSession> prepareFilmSession(int n, int cinemaIdMax, int filmIdMax) {
        List<FilmSession> ans = new ArrayList<>();
        Date d = new Date();
        for (int i = 0; i < n; i++) {
            String hall = "hall" + Integer.toString(i);
            String classification = "classification" + Integer.toString(random.nextInt(n));
            long a = random.nextLong() > 0?random.nextLong()%86400000L:(-random.nextLong())%86400000L;
            Timestamp beginTime = new Timestamp(d.getTime() + a);
            Timestamp endTime = new Timestamp(beginTime.getTime() + 1296000000L);
            int cinemaId = random.nextInt(cinemaIdMax)+1;
            int hallSittingId = i+1;
            int filmId = random.nextInt(filmIdMax)+1;
            double price = random.nextDouble() * 15;
            FilmSession filmSession = new FilmSession(hall, beginTime, endTime, classification, price, cinemaId, hallSittingId, filmId);
            ans.add(filmSession);
        }
        return ans;
    }

    public static List<HallSitting> prepareHallSitting(int n, int x, int y) {
        List<HallSitting> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<OneSit> oneSits = new ArrayList<>();
            for (int j = 0; j < x*y; j++) {
                int ax = j/y, ay = j%x;
                oneSits.add(new OneSit(ax, ay, true));
            }
            JSONArray array = JSONArray.fromObject(oneSits.toArray());
            HallSitting hallSitting = new HallSitting();
            hallSitting.setSit(array.toString());
            ans.add(hallSitting);
        }
        return ans;
    }

    public static List<Reservation> prepareOrder(int n, int userIdMax, int filmSessionIdMax, int x, int y) {
        List<Reservation> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            OneSit oneSit = new OneSit(i, i, true);
            String sitting = "[{\"x\":"+Integer.toString(i/y)+",\"y\":"+Integer.toString(i%y)+"}]";
            double price = random.nextDouble()*15;
            int filmSessionId = random.nextInt(filmSessionIdMax)+1;
            int userId = random.nextInt(userIdMax)+1;
            ans.add(new Reservation(sitting, price, filmSessionId, userId));
        }
        return ans;
    }


    public static List<User> prepareUser(int n) {
        List<User> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String username = "username" + Integer.toString(i);
            String password = "password" + Integer.toString(i);
            String email = Integer.toString(i) + "@qq.com";
            String code = CodeUtil.generateUniqueCode();
            User user = new User(username, password, email, code);
            ans.add(user);
        }
        return ans;
    }
}
