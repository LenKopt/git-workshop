package pl.akademiaspecjalistow.oop.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> listOfBooks;
    private Map<Reader, List<Book>> listReaders;

    public Library() {
        this.listOfBooks = new ArrayList<>();
        this.listReaders = new HashMap();
    }

    public static Book createNewBook(String title, String author, Integer year) {
        return new Book(title, author, year);
    }

    public void addToLibrary(String title, String author, Integer year) {
        Book currentBook = findBook(title, author, year);
        if (currentBook == null) {
            currentBook = createNewBook(title, author, year);
            listOfBooks.add(currentBook);
        }
        currentBook.increaseCountOfInstance();
        currentBook.increaseRemainder();
    }

    private Book findBook(String title, String author, Integer year) {
        for (Book nextBook : listOfBooks) {
            if (nextBook.getTitle().equals(title) && nextBook.getAuthor().equals(author) && nextBook.getYear().equals(year)) {
                return nextBook;
            }
        }
        return null;
    }


    public void findBook(String stringParametr, Boolean isAuthor, Integer intParametr) {
        List<Book> listSearchBooks = new ArrayList<>();
        for (Book nextBook : listOfBooks) {
            if (stringParametr.equals("")) {
                if (nextBook.getYear().equals(intParametr)) {
                    listSearchBooks.add(nextBook);
                } else if (nextBook.getRemainder().compareTo(intParametr) == 0 || nextBook.getRemainder().compareTo(intParametr) == 1) {
                    listSearchBooks.add(nextBook);
                }
            } else if (isAuthor) {
                if (nextBook.getAuthor().toLowerCase().contains(stringParametr.toLowerCase())) {
                    listSearchBooks.add(nextBook);
                }
            } else {
                if (nextBook.getTitle().toLowerCase().contains(stringParametr.toLowerCase())) {
                    listSearchBooks.add(nextBook);
                }
            }
        }
        showToConsole(listSearchBooks);
    }


    public void borrowBook(Reader reader, String title, String author, Integer year) {
        Book foundBook = findBook(title, author, year);
        if (foundBook == null) {
            System.out.println("Sorry! This book isn't in our library!");
        } else if (foundBook.getRemainder() == 0) {
            System.out.println("Sorry! This book already borrowed!");
        } else {
            List<Book> listBookOfReader = listReaders.get(reader);
            if (listBookOfReader == null) {
                listBookOfReader = new ArrayList<>();
                listBookOfReader.add(foundBook);
                listReaders.put(reader, listBookOfReader);
            } else {
                listBookOfReader.add(foundBook);
            }
            foundBook.decreaseRemainder();
        }
    }

    public void returnBook(Reader reader, String title, String author, Integer year) {

        List<Book> listBookOfReader = listReaders.get(reader);
        if (listBookOfReader == null) {
            System.out.println("Error! Check entered data!");
        } else {
            Book currentBook = findBook(title, author, year);
            listBookOfReader.remove(currentBook);
            currentBook.increaseRemainder();
        }
    }

    public void showListOfBooks() {
        showToConsole(listOfBooks);
    }

    private void showToConsole(List<Book> listToShow) {
        System.out.println("LIST OF BOOKS\n");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("|            Author            |             Title            |  Year  | Count | Reminder |");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Book nextBook : listToShow) {
            System.out.println("|" + addSpace(nextBook.getAuthor().trim(), 30) + "|" + addSpace(nextBook.getTitle().trim(), 30)
                    + "|  " + nextBook.getYear() + "  |   "
                    + nextBook.getCountOfInstance() + "   |     " + nextBook.getRemainder() + "    |");
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static String addSpace(String word, int lenghtString) {
        String retundWork = word;
        for (int i = 0; i < lenghtString - word.length(); i++) {
            retundWork = retundWork + " ";
        }
        return retundWork;
    }

    public void printListOfReaders() {
        System.out.println("LIST BORROWED BOOKS\n");

        for (Map.Entry<Reader, List<Book>> pair : listReaders.entrySet()) {
            Reader reader = pair.getKey();
            List<Book> listRedersBooks = pair.getValue();
            String readersBooks = listRedersBooks.toString();
            System.out.println(reader + " : " + readersBooks);
        }
    }

    public Reader returnReader(String firstName, String lastName) {

        for (Map.Entry<Reader, List<Book>> pair : listReaders.entrySet()) {
            Reader reader = pair.getKey();
            if (reader.getFirstName().trim().toLowerCase().equals(firstName) && reader.getLastName().trim().toLowerCase().equals(lastName)) {
                return reader;
            }
        }
        return null;
    }

    public void removeBook(String title, String author, Integer year) {
        Book foundBook = findBook(title, author, year);
        Integer reminderThisBook = foundBook.getRemainder();
        Integer countThisBook = foundBook.getCountOfInstance();

        if (foundBook == null) {
            System.out.println("This book isn't in our library");
        } else if (reminderThisBook == 1 && countThisBook == 1) {
            System.out.println("Book " + foundBook.getTitle() + " will be delete!");
            listOfBooks.remove(foundBook);
        } else if (reminderThisBook >= 1) {
            foundBook.decreaseRemainder();
            foundBook.decreaseCount();
        } else {
            System.out.println("This book can be deleted!");
        }
    }
}
