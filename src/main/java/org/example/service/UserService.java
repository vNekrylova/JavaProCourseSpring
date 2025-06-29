package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) {
        createUser("Рия");

        getAllUsers().forEach(user -> log.info("Found user {}", user));

        getUserById(4L).ifPresent(user -> log.info("Found user {}", user));

        updateUser(4L, "Лия");

        deleteUser(4L);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(String username) {
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        log.info("Пользователь создан: {}", username);
    }

    public void updateUser(Long id, String username) {
        Optional<User> userFind = userRepository.findById(id);

        if (userFind.isPresent()) {
            userRepository.save(new User(id, username));
            log.info("Пользователь с id {} успешно изменен", id);
        }
        else {
            log.info("Пользователь с id {} не найден", id);
        }
    }

    public void deleteUser(Long id) {
        Optional<User> userFind = userRepository.findById(id);
        if (userFind.isPresent()) {
            userRepository.delete(userFind.get());
            log.info("Пользователь с id {} успешно удален", id);
        }
        else {
            log.info("Пользователь с id {} не найден", id);
        }
    }
}
