package cz.streicher.dao;


import cz.streicher.models.Book;
import cz.streicher.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book get(int bookId) {
        return jdbcTemplate.query("SELECT* FROM books WHERE book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO books(title,author,release_year) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getRelease_year());

    }

    public void edit(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE books SET title=?,author=? ,release_year=?  WHERE book_id=?",
                updatedBook.getTitle(), updatedBook.getTitle(), updatedBook.getRelease_year(), bookId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM books WHERE book_id=?", bookId);
    }

    public User getLoan(int bookId){
        return jdbcTemplate.query("SELECT full_name FROM users join books b on users.user_id = b.user_id WHERE book_id=?",
                new Object[]{bookId}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void deleteUser(int bookId){
        jdbcTemplate.update("UPDATE books SET user_id=null WHERE book_id=?",bookId);
    }

    public void appoint(int userId,int bookId) {
        jdbcTemplate.update("UPDATE books SET user_id = ? WHERE book_id = ?",userId, bookId );
    }

}
