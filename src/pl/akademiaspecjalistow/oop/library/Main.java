package pl.akademiaspecjalistow.oop.library;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library myLibrary = new Library();
        myLibrary.addToLibrary("Przygody Tomka sawyera", "Twain Mark", 2000);
        myLibrary.addToLibrary("Pan Tadeusz", "Mickiewicz Adam", 1985);
        myLibrary.addToLibrary("Pan Tadeusz", "Mickiewicz Adam", 1985);
        myLibrary.addToLibrary("Pan Tadeusz", "Mickiewicz Adam", 1985);
        myLibrary.addToLibrary("Pan Tadeusz", "Mickiewicz Adam", 1980);
        myLibrary.addToLibrary("Ogniem i mieczem", "Sienkiewicz Henryk", 2000);
        myLibrary.addToLibrary("Ogniem i mieczem", "Sienkiewicz Henryk", 2000);
        myLibrary.addToLibrary("Rusłan i Ludmiła", "Puszkin Aleksandr", 1920);

        int result = showMenu();

        while (result != 8) {

            switch (result) {
                case 1 -> {
                    myLibrary.showListOfBooks();
                }
                case 2 -> {
                    int searchСriterion = getSearchCriterion();
                    showBooks(searchСriterion, myLibrary);
                }
                case 3 ->
                        myLibrary.borrowBook(getReader(myLibrary), getString("Enter title of book"), getString("Enter author"), Integer.parseInt(getString("Enter year")));
                case 4 -> {
                    System.out.println("Please, enter title of book, its author and year of new book:");
                    myLibrary.addToLibrary(getString("Enter title of book"), getString("Enter author"), Integer.parseInt(getString("Enter year")));
                }
                case 5 -> {
                    System.out.println("Please, enter title of book, its author and year of new book, that will be deleted:");
                    myLibrary.removeBook(getString("Enter title of book"), getString("Enter author"), Integer.parseInt(getString("Enter year")));
                }
                case 6 ->
                        myLibrary.returnBook(getReader(myLibrary), getString("Enter title of book"), getString("Enter author"), Integer.parseInt(getString("Enter year")));
                case 7 -> myLibrary.printListOfReaders();
                case 8 -> System.exit(0);
            }
            result = showMenu();
        }
    }

    private static void showBooks(int searchСriterion, Library library) {
        switch (searchСriterion) {
            case 1 -> {
                library.findBook(getString("Enter author"), true, 0);
            }
            case 2 -> {
                library.findBook(getString("Enter the title"), false, 0);
            }
            case 3 -> {
                library.findBook("", false, Integer.parseInt(getString("Enter year")));
            }
            case 4 -> {
                library.findBook("", false, Integer.parseInt(getString("Enter reminder")));
            }
        }
    }

    private static int getSearchCriterion() {
        System.out.println("\nTo indicate only the author enter 1:\n" +
                "To indicate only title enter 2:\n" +
                "To indicate only year enter 3:\n" +
                "To view the list of books with a reminder greater than specified enter 4:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static Reader getReader(Library library) {
        String firstName = "";
        String lastName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name of reader:");
        if (scanner.hasNextLine()) {
            firstName = scanner.nextLine();
        }
        System.out.println("Enter last name of reader:");
        if (scanner.hasNextLine()) {
            lastName = scanner.nextLine();
        }
        //scanner.close();

        Reader reader = library.returnReader(firstName, lastName);
        if (reader == null) {
            return new Reader(firstName, lastName);
        }
        return reader;
    }

    private static String getString(String textForUser) {
        System.out.println(textForUser);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            //scanner.close();
            return scanner.nextLine();
        }

        return "";
    }

    private static int showMenu() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nShow book collections - enter 1:\n" +
                    "Search for book - enter 2:\n" +
                    "Borrow a book - enter 3:\n" +
                    "Add a book - enter 4:\n" +
                    "Remove a book - enter 5:\n" +
                    "Return a book - enter 6:\n" +
                    "Print list of readers - enter 7:\n" +
                    "Exit - enter 8:\n");
            try {

                input = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                //scanner.nextLine();
            } catch (NoSuchElementException e) {
                //scanner.nextLine();
            }
        } while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5 && input != 6 && input != 7 && input != 8);

        if (input == 8) {
            scanner.close();
        }
        return input;
    }
}
