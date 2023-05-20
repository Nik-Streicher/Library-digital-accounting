package cz.streicher.dao;


import cz.streicher.models.Book;
import cz.streicher.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User get(int userId) {
        return jdbcTemplate.query("SELECT* FROM users WHERE user_id=?", new Object[]{userId}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }


    public void add(User user) {
        jdbcTemplate.update("INSERT INTO users(full_name,year_of_birth) VALUES (?,?)", user.getFull_name(), user.getYear_of_birth());

    }

    public void edit(int userId, User updatedUser) {
        jdbcTemplate.update("UPDATE users SET full_name=?, year_of_birth=?  WHERE user_id=?",
                updatedUser.getFull_name(), updatedUser.getYear_of_birth(), userId);
    }

    public void delete(int userId) {
        jdbcTemplate.update("DELETE FROM users WHERE user_id=?", userId);
    }


    public List<Book> getUserBooks(int userId){
        return jdbcTemplate.query("SELECT title, author, release_year FROM users JOIN books b on users.user_id = b.user_id WHERE b.user_id = ?",
                new Object[]{userId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<User> getUser(String fullName){
        return jdbcTemplate.query("SELECT* FROM users WHERE full_name LIKE ?", new Object[]{fullName}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny();
    }
}
