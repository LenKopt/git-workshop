package pl.akademiaspecjalistow.oop.library;

public class Book {
    private String title;
    private String author;
    private Integer year;
    private Integer countOfInstance;
    private Integer remainder;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.countOfInstance = 0;
        this.remainder = 0;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public void increaseCountOfInstance() {
        this.countOfInstance++;
    }

    public void increaseRemainder() {
        this.remainder++;
    }

    public Integer getCountOfInstance() {
        return countOfInstance;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void decreaseRemainder() {
        this.remainder--;
    }

    @Override
    public String toString() {
        return "Book: " + title + " of " + author + ", " + year;
    }

    public void decreaseCount() {
        this.countOfInstance--;
    }
}
