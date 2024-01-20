import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        char[][] field = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        showField(field);
        boolean gameIsFinish = false;
        int currentPlayer = 1;
        Scanner scanner = new Scanner(System.in);

        while (!gameIsFinish) {

            showRequest(currentPlayer);
            String getAnswer = scanner.nextLine();
            getPairRowColumb(getAnswer, field, currentPlayer);
            gameIsFinish = checkForWin(field);

            showField(field);

            if (gameIsFinish) {
                System.out.println("You are win!");
                break;
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;

        }
        scanner.close();

    }

    private static void getPairRowColumb(String answer, char[][] field, int currentPlayer) {
        String[] pairRowColumn;
        pairRowColumn = answer.split(" ");
        pasteToField(Integer.valueOf(pairRowColumn[0]), Integer.valueOf(pairRowColumn[1]), field, currentPlayer);
    }

    private static boolean checkForWin(char[][] fields) {
        boolean winRow = checkForWinRows(fields);
        System.out.println(winRow);
        if (winRow) {
            return true;
        }
        boolean winColumns = checkForWinColumns(fields);
        System.out.println(winColumns);
        if (winColumns) {
            return true;
        }
        boolean winDiagonals = checkForWinDiagonals(fields);
        System.out.println(winDiagonals);
        if (winDiagonals) {
            return true;
        }

        return winDiagonals || winRow || winColumns;
    }

    private static boolean checkForWinDiagonals(char[][] fields) {

        if (fields[2][2] == '-') {
            return false;
        }

        if ((fields[1][1] == fields[0][0]) && (fields[2][2] == fields[1][1])) {
            return true;
        }
        if ((fields[1][1] == fields[0][2]) && (fields[1][1] == fields[2][0])) {
            return true;
        }

        return false;
    }

    private static boolean checkForWinColumns(char[][] fields) {
        for (int i = 0; i < 3; i++) {
            if (fields[0][i] == '-') {
                return false;
            }

            if ((fields[0][i] == fields[1][i]) && (fields[0][i] == fields[2][i])) {
                return true;
            }

        }
        return false;
    }

    private static boolean checkForWinRows(char[][] fields) {

        for (int i = 0; i < 3; i++) {
            if (fields[i][0] == '-') {
                return false;
            }

            if ((fields[i][0] == fields[i][1]) && (fields[i][0] == fields[i][2])) {
                return true;
            }

        }
        return false;
    }

    private static void pasteToField(Integer row, Integer column, char[][] field, int numberOfPlayer) {

        field[row - 1][column - 1] = numberOfPlayer == 1 ? 'x' : '0';

    }

    private static void showField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void showRequest(int numberPlayer) {
        System.out.println("Player " + numberPlayer + ", please, write firstly row, then column");
    }
}
