package cz.streicher.models;

public class Book {
    private String title;
    private String author;
    private int release_year;

    public Book(String title, String author, int release_year) {
        this.title = title;
        this.author = author;
        this.release_year = release_year;
    }

    public Book(){
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



}
