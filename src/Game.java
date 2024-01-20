import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        char[][] field = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        showField(field);
        boolean gameIsFinish = false;
        boolean winIsFound = false;
        int currentPlayer = 1;
        Scanner scanner = new Scanner(System.in);

        while (!winIsFound&&!gameIsFinish) {

            showRequest(currentPlayer);
            String getAnswer = scanner.nextLine();
            String[] pairRowColumn = getPairRowColumn(getAnswer, field, currentPlayer);

            boolean isCorrectEnter = checkCorrectEnter(pairRowColumn, field);
            if (isCorrectEnter) {
                pasteToField(pairRowColumn, field, currentPlayer);
            } else {
                continue;
            }

            winIsFound = checkForWin(field);
            gameIsFinish = checkFinishedGame(field);
            showField(field);

            if (winIsFound) {
                System.out.println("You are win!");
            }
            if (gameIsFinish) {
                System.out.println("Game over!");
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;

        }
        scanner.close();

    }

    private static boolean checkFinishedGame(char[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkCorrectEnter(String[] pairRowColumn, char[][] field) {

        int numberRow = Integer.valueOf(pairRowColumn[0]);
        int numberColumn = Integer.valueOf(pairRowColumn[1]);

        if (numberRow >= 1 && numberRow <= 3 && numberColumn >= 1 && numberColumn <= 3) {
            if (field[numberRow - 1][numberColumn - 1] == '-') {
                return true;
            } else {
                System.out.println("This place is not empty!");
            }
        }
        System.out.println("You write wrong number row or column");
        return false;
    }

    private static String[] getPairRowColumn(String answer, char[][] field, int currentPlayer) {
        String[] pairRowColumn;
        pairRowColumn = answer.split(" ");

        return pairRowColumn;
    }

    private static boolean checkForWin(char[][] fields) {
        boolean winRow = checkForWinRows(fields);
        //System.out.println(winRow);
        if (winRow) {
            return true;
        }
        boolean winColumns = checkForWinColumns(fields);
        //System.out.println(winColumns);
        if (winColumns) {
            return true;
        }
        boolean winDiagonals = checkForWinDiagonals(fields);
        //System.out.println(winDiagonals);
        if (winDiagonals) {
            return true;
        }

        return winDiagonals || winRow || winColumns;
    }

    private static boolean checkForWinDiagonals(char[][] fields) {

        if (fields[1][1] == '-') {
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

    private static void pasteToField(String[] pairRowColumn, char[][] field, int numberOfPlayer) {

        int numberRow = Integer.valueOf(pairRowColumn[0]);
        int numberColumn = Integer.valueOf(pairRowColumn[1]);
        field[numberRow - 1][numberColumn - 1] = numberOfPlayer == 1 ? 'x' : '0';

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
