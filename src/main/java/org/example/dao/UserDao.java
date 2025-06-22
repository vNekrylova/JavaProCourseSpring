package org.example.dao;

import org.example.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                .stream().findFirst();
    }

    public void create(String username) {
        String sql = "INSERT INTO users (username) VALUES (?)";
        jdbcTemplate.update(sql, username);
    }

    public void update(User user) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
