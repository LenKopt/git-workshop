package pl.akademiaspecjalistow.oop.library;

import java.util.List;
import java.util.Map;

public class Utils {
    private Utils() {
    }

    static String addSpace(String word, int lenghtString) {
        String retundWork = word;
        for (int i = 0; i < lenghtString - word.length(); i++) {
            retundWork = retundWork + " ";
        }
        return retundWork;
    }

    static void showToConsole(List<Book> listToShow) {
        System.out.println("LIST OF BOOKS\n");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("|            Author            |             Title            |  Year  | Status |");
        System.out.println("---------------------------------------------------------------------------------");
        for (Book nextBook : listToShow) {
            System.out.println("|" + Utils.addSpace(nextBook.getAuthor().getNameAuthor(), 30) +
                    "|" + Utils.addSpace(nextBook.getTitle(), 30) +
                    "|" + Utils.addSpace(nextBook.getYear().toString(), 8) +
                    "|" + Utils.addSpace(nextBook.getStatus().toString(), 8) + "|" + nextBook.getId());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    public static void printListOfReaders(Map<Reader, List<Book>> listReaders) {
        System.out.println("LIST OF READERS\n");
        for (Map.Entry<Reader, List<Book>> pair : listReaders.entrySet()) {
            Reader reader = pair.getKey();
            List<Book> listRedersBooks = pair.getValue();
            String readersBooks = listRedersBooks.toString();
            System.out.println(reader + " : " + readersBooks);
        }
    }
}
