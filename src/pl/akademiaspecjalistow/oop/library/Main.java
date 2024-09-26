package pl.akademiaspecjalistow.oop.library;

import java.time.Year;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library myLibrary = new Library();
        myLibrary.addToLibrary("Przygody Tomka sawyera", myLibrary.findAuthor("Twain Mark"), 2000);
        myLibrary.addToLibrary("Pan Tadeusz", myLibrary.findAuthor("Mickiewicz Adam"), 1985);
        myLibrary.addToLibrary("Pan Tadeusz", myLibrary.findAuthor("Mickiewicz Adam"), 1985);
        myLibrary.addToLibrary("Dog", myLibrary.findAuthor("Kot Tom"), 1985);
        myLibrary.addToLibrary("Pan Tadeusz", myLibrary.findAuthor("Mickiewicz Adam"), 1980);
        myLibrary.addToLibrary("Ogniem i mieczem", myLibrary.findAuthor("Sienkiewicz Henryk"), 2000);
        myLibrary.addToLibrary("Ogniem i mieczem", myLibrary.findAuthor("Sienkiewicz Henryk"), 2000);
        myLibrary.addToLibrary("Rusłan i Ludmiła", myLibrary.findAuthor("Puszkin Aleksandr"), 1920);

        int result = showMainMenu(scanner);

        List<Integer> listPossibleOptions = List.of(1, 2, 3, 4, 5, 6, 7);

        while (listPossibleOptions.contains(result)) {

            switch (result) {
                case 1 -> {
                    myLibrary.showListOfBooks();
                }
                case 2 -> {
                    int searchСriterion = getSelectionSearchMenu();
                    if (searchСriterion != 0) {
                        processSelection(searchСriterion, myLibrary, scanner);
                    }
                }
                case 3 -> {
                    System.out.println("Please, enter title of book, its author and year of new book:");
                    myLibrary.addToLibrary(getStringFromConsole("Enter title of book", scanner),
                            myLibrary.findAuthor(getStringFromConsole("Enter the author", scanner)),
                            getIntFromConsole("Enter the year", scanner));
                }
                case 4 -> {
                    System.out.println("Please, enter title of book, its author and year of book, that will be deleted:");
                    boolean wasDeleted = myLibrary.removeBook(getStringFromConsole("Enter title of book", scanner),
                            myLibrary.findAuthor(getStringFromConsole("Enter author", scanner)),
                            getIntFromConsole("Enter year", scanner));
                    if (wasDeleted) {
                        System.out.println("THE BOOK WAS DELETED SUCCESSFULLY!");
                    } else {
                        System.out.println("THE BOOK WAS NOT DELETED. THE BOOK WAS NOT FOUND OR WAS BORROWED!");
                    }
                }
                case 5 -> {
                    boolean resultBorrowing = myLibrary.borrowBook(myLibrary.getReader(getStringFromConsole("Enter the first name of reader", scanner),
                                    getStringFromConsole("Enter the second name of reader", scanner)),
                            getStringFromConsole("Enter title of book", scanner),
                            myLibrary.findAuthor(getStringFromConsole("Enter author", scanner)),
                            getIntFromConsole("Enter year", scanner));
                    if (resultBorrowing) {
                        System.out.println("THE BOOK WAS BORROWED SUCCESSFULLY!");
                    } else {
                        System.out.println("THE BOOK WAS NOT BORROWED!");
                    }
                }
                case 6 -> {
                    boolean wasReturn = myLibrary.returnBook(myLibrary.getReader(getStringFromConsole("Enter the first name of reader", scanner),
                                    getStringFromConsole("Enter the second name of reader", scanner)),
                            getStringFromConsole("Enter title of book", scanner),
                            myLibrary.findAuthor(getStringFromConsole("Enter the author", scanner)),
                            getIntFromConsole("Enter the year", scanner));
                    if (wasReturn) {
                        System.out.println("THE BOOK WAS RETURNED SUCCESSFULLY!");
                    } else {
                        System.out.println("THE BOOK WAS NOT RETURNED. THE BOOK WAS NOT FOUND OR WAS NOT BORROWED THIS READER!");
                    }
                }
                case 7 -> Utils.printListOfReaders(myLibrary.getListReaders());
                case 8 -> System.exit(0);
            }
            result = showMainMenu(scanner);
        }
        scanner.close();
    }

    private static void processSelection(int searchСriterion, Library library, Scanner scanner) {
        List<Book> listFoundBooks = new ArrayList<>();
        switch (searchСriterion) {
            case 1 -> {
                String authorName = getStringFromConsole("Enter the author name", scanner);
                listFoundBooks.addAll(library.findBookByAuthor(authorName));
            }
            case 2 -> {
                listFoundBooks.addAll(library.findBookByTitle(getStringFromConsole("Enter the title of book", scanner)));
            }
            case 3 -> {
                listFoundBooks.addAll(library.findBookByYear(getIntFromConsole("Enter the year", scanner)));
            }
            case 4 -> {
                String informationFromUser = getStringFromConsole("Enter the status - y/n", scanner);
                listFoundBooks.addAll(library.findBookByStatus(informationFromUser.toUpperCase().trim()));
            }
        }
        Utils.showToConsole(listFoundBooks);
    }

    private static int getSelectionSearchMenu() {
        System.out.println("\nTo indicate only the author enter 1:\n" +
                "To indicate only title enter 2:\n" +
                "To indicate only year enter 3:\n" +
                "To indicate free books enter 4:");
        Scanner scanner = new Scanner(System.in);
        List<Integer> listPossibleOptions = List.of(1, 2, 3, 4);
        try {
            int result = scanner.nextInt();
            if (listPossibleOptions.contains(result)) {
                return result;
            }
        } catch (InputMismatchException e) {
            System.out.println("It is not int number!");
        }
        System.out.println("You can write int number from 1 to 4!");
        return 0;
    }

    private static String getStringFromConsole(String textForUser, Scanner scanner) {
        System.out.println(textForUser);
        if (textForUser.contains("status")) {
            List<String> listYesNo = List.of("Y", "N", "YES", "NO");
            while (scanner.hasNextLine()) {
                String currentValue = scanner.nextLine().toUpperCase();
                if (listYesNo.contains(currentValue)) {
                    return currentValue.substring(0, 1).toUpperCase();
                }
                System.out.println("WRITE Y/N");
            }
        }
        if (scanner.hasNextLine()) {
            return scanner.nextLine().toUpperCase();
        }
        return "";
    }

    private static Integer getIntFromConsole(String textForUser, Scanner scanner) {
        System.out.println(textForUser);
        while (scanner.hasNextLine()) {
            try {
                if (textForUser.contains("year")) {
                    Integer currentValue = scanner.nextInt();
                    if (currentValue > 0 && currentValue <= Year.now().getValue()) {
                        return currentValue;
                    }
                    System.out.println("Year can be more than 0 and less or equal this year");
                } else {
                    if (scanner.hasNextLine()) {
                        return scanner.nextInt();
                    }
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("WRITE YEAR, FOR EXAMPLE 2000, 1990;");
            }
        }
        return 0;
    }

    private static int showMainMenu(Scanner scanner) {
        int input = 0;
        System.out.println("\nShow book collections - enter 1:\n" +
                "Search for book - enter 2:\n" +
                "Add a book - enter 3:\n" +
                "Remove a book - enter 4:\n" +
                "Borrow a book - enter 5:\n" +
                "Return a book - enter 6:\n" +
                "Print list of readers - enter 7:\n" +
                "Exit - enter *:\n");
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {

        } catch (NoSuchElementException e) {

        }
        return input;
    }
}
