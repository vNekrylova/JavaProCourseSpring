package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    public void createUser(String username) {
        userDao.create(username);
        System.out.println("Пользователь создан: " + username);
    }

    public void updateUser(Long id, String username) {
        Optional<User> userFind = userDao.findById(id);

        if (userFind.isPresent()) {
            userDao.update(new User(id, username));
            System.out.println("Пользователь с id " + id + " успешно изменен");
        }
        else {
            System.out.println("Пользователь с id " + id + " не найден");
        }
    }

    public void deleteUser(Long id) {
        Optional<User> userFind = userDao.findById(id);
        if (userFind.isPresent()) {
            userDao.delete(id);
            System.out.println("Пользователь с id " + id + " успешно удален");
        }
        else {
            System.out.println("Пользователь с id " + id + " не найден");
        }
    }
}
