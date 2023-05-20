package cz.streicher.models;

import javax.validation.constraints.NotEmpty;

public class Book {

    private int book_id;
    @NotEmpty(message = "Title should be not empty")
    private String title;
    private String author;

    private int release_year;

    public Book(String title, String author, int release_year, int book_id) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.release_year = release_year;
    }


    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

}
