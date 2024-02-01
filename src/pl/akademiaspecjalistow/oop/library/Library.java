package pl.akademiaspecjalistow.oop.library;

import java.util.*;

public class Library {
    private List<Book> listOfBooks;
    private Map<Reader, List<Book>> listReaders;
    private Set<Author> setAuthors;

    public Library() {
        this.listOfBooks = new ArrayList<>();
        this.listReaders = new HashMap();
        this.setAuthors = new HashSet<>();
    }

    public Map<Reader, List<Book>> getListReaders() {
        return listReaders;
    }

    public static Book createNewBook(String title, Author author, Integer year) {
        return new Book(title, author, year);
    }

    public void addToLibrary(String title, Author author, Integer year) {
        Book currentBook = createNewBook(title, author, year);
        listOfBooks.add(currentBook);
    }

    private Book findBook(String title, Author author, Integer year, String status) {
        for (Book nextBook : listOfBooks) {
            if (nextBook.getTitle().equals(title) &&
                    nextBook.getAuthor().equals(author) &&
                    nextBook.getYear().equals(year) &&
                    nextBook.getStatus().equals(status)) {
                return nextBook;
            }
        }
        return null;
    }

    List<Book> findBookByAuthor(Author author) {
        List<Book> listSearchBooks = new ArrayList<>();
        for (Book nextBook : listOfBooks) {
            if (nextBook.getAuthor().equals(author)) {
                listSearchBooks.add(nextBook);
            }
        }
        return listSearchBooks;
    }

    List<Book> findBookByTitle(String title) {
        List<Book> listSearchBooks = new ArrayList<>();
        for (Book nextBook : listOfBooks) {
            if (nextBook.getTitle().equals(title)) {
                listSearchBooks.add(nextBook);
            }
        }
        return listSearchBooks;
    }

    List<Book> findBookByYear(Integer year) {
        List<Book> listSearchBooks = new ArrayList<>();
        for (Book nextBook : listOfBooks) {
            if (nextBook.getYear().equals(year)) {
                listSearchBooks.add(nextBook);
            }
        }
        return listSearchBooks;
    }

    List<Book> findBookByStatus(String status) {
        List<Book> listSearchBooks = new ArrayList<>();
        for (Book nextBook : listOfBooks) {
            if (nextBook.getStatus().equals(status)) {
                listSearchBooks.add(nextBook);
            }
        }
        return listSearchBooks;
    }

    public boolean borrowBook(Reader reader, String title, Author author, Integer year) {
        Book foundBook = findBook(title, author, year, "Y");
        if (foundBook == null) {
            return false;
        } else if (foundBook.getStatus().equals("N")) {
            return false;
        }
        foundBook.changeStatus();
        writeBookForReader(reader, foundBook);
        return true;
    }

    private void writeBookForReader(Reader reader, Book book) {
        List<Book> listBookOfReader = listReaders.get(reader);
        if (listBookOfReader == null) {
            listBookOfReader = new ArrayList<>();
            listBookOfReader.add(book);
            listReaders.put(reader, listBookOfReader);
        } else {
            listBookOfReader.add(book);
        }
    }

    public boolean returnBook(Reader reader, String title, Author author, Integer year) {

        List<Book> listBookOfReader = listReaders.get(reader);
        if (listBookOfReader == null) {
            return false;
        }
        Book currentBook = findBook(title, author, year, "N");
        if (listBookOfReader.remove(currentBook)) {
            currentBook.changeStatus();
            return true;
        }
        return false;
    }

    public void showListOfBooks() {
        Utils.showToConsole(listOfBooks);
    }

    public Reader returnReader(String firstName, String lastName) {

        for (Map.Entry<Reader, List<Book>> pair : listReaders.entrySet()) {
            Reader reader = pair.getKey();
            if (reader.getFirstName().equalsIgnoreCase(firstName) && reader.getLastName().equalsIgnoreCase(lastName)) {
                return reader;
            }
        }
        return null;
    }

    public boolean removeBook(String title, Author author, Integer year) {
        Book foundBook = findBook(title.toUpperCase().trim(), author, year, "Y");
        if (foundBook == null) {
            return false;
        } else {
            listOfBooks.remove(foundBook);
            return true;
        }
    }

    Author findAuthor(String nameAuthor) {
        for (Author nextAuthor : setAuthors) {
            if (nextAuthor.getNameAuthor().equals(nameAuthor.toUpperCase().trim())) {
                return nextAuthor;
            }
        }
        Author newAuthor = new Author(nameAuthor);
        setAuthors.add(newAuthor);
        return newAuthor;
    }
    Reader getReader(String firstName, String lastName) {

        Reader reader = returnReader(firstName, lastName);
        if (reader == null) {
            return new Reader(firstName, lastName);
        }
        return reader;
    }
}
