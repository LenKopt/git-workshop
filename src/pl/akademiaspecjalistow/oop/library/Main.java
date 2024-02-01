package pl.akademiaspecjalistow.oop.library;

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

        int result = showMenu(scanner);

        while (result != 8) {

            switch (result) {
                case 1 -> {
                    myLibrary.showListOfBooks();
                }
                case 2 -> {
                    int searchСriterion = getSearchCriterion();
                    showBooks(searchСriterion, myLibrary, scanner);
                }
                case 3 -> {
                    System.out.println("Please, enter title of book, its author and year of new book:");
                    myLibrary.addToLibrary(getInformationFromConsol("Enter title of book", scanner),
                            myLibrary.findAuthor(getInformationFromConsol("Enter the author", scanner)),
                            Integer.parseInt(getInformationFromConsol("Enter the year", scanner)));
                }
                case 4 -> {
                    System.out.println("Please, enter title of book, its author and year of book, that will be deleted:");
                    boolean wasDeleted = myLibrary.removeBook(getInformationFromConsol("Enter title of book", scanner),
                            myLibrary.findAuthor(getInformationFromConsol("Enter author", scanner)),
                            Integer.parseInt(getInformationFromConsol("Enter year", scanner)));
                    if (wasDeleted) {
                        System.out.println("THE BOOK WAS DELETED SUCCESSFULLY!");
                    } else {
                        System.out.println("THE BOOK WAS NOT DELETED. THE BOOK WAS NOT FOUND OR WAS BORROWED!");
                    }
                }
                case 5 -> {
                    boolean resultBorrowing = myLibrary.borrowBook(myLibrary.getReader(getInformationFromConsol("Enter the first name of reader", scanner),
                                    getInformationFromConsol("Enter the second name of reader", scanner)),
                            getInformationFromConsol("Enter title of book", scanner),
                            myLibrary.findAuthor(getInformationFromConsol("Enter author", scanner)),
                            Integer.parseInt(getInformationFromConsol("Enter year", scanner)));
                    if (resultBorrowing) {
                        System.out.println("THE BOOK WAS BORROWED SUCCESSFULLY!");
                    } else {
                        System.out.println("THE BOOK WAS NOT BORROWED!");
                    }
                }
                case 6 -> {
                    boolean wasReturn = myLibrary.returnBook(myLibrary.getReader(getInformationFromConsol("Enter the first name of reader", scanner),
                                    getInformationFromConsol("Enter the second name of reader", scanner)),
                            getInformationFromConsol("Enter title of book", scanner),
                            myLibrary.findAuthor(getInformationFromConsol("Enter the author", scanner)),
                            Integer.parseInt(getInformationFromConsol("Enter the year", scanner)));
                    if (wasReturn) {
                        System.out.println("THE BOOK WAS RETURNED SUCCESSFULLY!");
                    } else {
                        System.out.println("THE BOOK WAS NOT RETURNED. THE BOOK WAS NOT FOUND OR WAS NOT BORROWED THIS READER!");
                    }
                }
                case 7 -> Utils.printListOfReaders(myLibrary.getListReaders());
                case 8 -> System.exit(0);
            }
            result = showMenu(scanner);
        }
        scanner.close();
    }

    private static void showBooks(int searchСriterion, Library library, Scanner scanner) {
        List<Book> listFoundBooks = new ArrayList<>();
        switch (searchСriterion) {
            case 1 -> {
                String informationFromUser = getInformationFromConsol("Enter the author name", scanner);
                listFoundBooks.addAll(library.findBookByAuthor(library.findAuthor(informationFromUser)));
            }
            case 2 -> {
                listFoundBooks.addAll(library.findBookByTitle(getInformationFromConsol("Enter the title of book", scanner)));
            }
            case 3 -> {
                listFoundBooks.addAll(library.findBookByYear(Integer.parseInt(getInformationFromConsol("Enter the year", scanner))));
            }
            case 4 -> {
                String informationFromUser = getInformationFromConsol("Enter the status - y/n", scanner);
                listFoundBooks.addAll(library.findBookByStatus(informationFromUser.toUpperCase().trim()));
            }
        }
        Utils.showToConsole(listFoundBooks);
    }

    private static int getSearchCriterion() {
        System.out.println("\nTo indicate only the author enter 1:\n" +
                "To indicate only title enter 2:\n" +
                "To indicate only year enter 3:\n" +
                "To indicate free books enter 4:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    private static String getInformationFromConsol(String textForUser, Scanner scanner) {
        System.out.println(textForUser);
        if (scanner.hasNextLine()) {
            return scanner.nextLine().toUpperCase();
        }
        return "";
    }

    private static int showMenu(Scanner scanner) {
        int input = 0;
        do {
            System.out.println("\nShow book collections - enter 1:\n" +
                    "Search for book - enter 2:\n" +
                    "Add a book - enter 3:\n" +
                    "Remove a book - enter 4:\n" +
                    "Borrow a book - enter 5:\n" +
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

        return input;
    }
}
