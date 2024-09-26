package pl.akademiaspecjalistow.oop.library;

public class Author {
    private String nameAuthor;

    public Author(String nameAuthor) {
        this.nameAuthor = nameAuthor.toUpperCase().trim();
    }

    public String getNameAuthor() {
        return nameAuthor;
    }
}
