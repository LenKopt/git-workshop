package pl.akademiaspecjalistow.oop.library;

import java.util.UUID;

public class Book {
    private String title;
    private Author author;
    private Integer year;
    private Boolean status;
    private String id;

    public Book(String title, Author author, Integer year) {
        this.title = title.toUpperCase().trim();
        this.author = author;
        this.year = year;
        this.status = true;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void changeStatus() {
        this.status = !status;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book: " + title + " of " + author.getNameAuthor() + ", " + year + "; ";
    }

    public String getStatus() {
        return status == true ? "Y" : "N";
    }
}
