package com.five.user.dao;

import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }
}
