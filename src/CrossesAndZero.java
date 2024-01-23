import java.util.*;
//1. разобраться с сообщением в начале

public class CrossesAndZero {
    public static void main(String[] args) {
        char[][] field;
        String firstNameOfPlayer = enterName("first");
        String secondNameOfPlayer = enterName("second");
        Map<String, Integer> tableOfWins = new HashMap<>();
        tableOfWins.put(firstNameOfPlayer, 0);
        tableOfWins.put(secondNameOfPlayer, 0);

        System.out.println("Give size the game (3-99):");
        Scanner scanner = new Scanner(System.in);
        int sizeField;

        while (true) {
            try {
                sizeField = scanner.nextInt();
                if (!(sizeField < 100 && sizeField > 2))
                    System.out.println("Wrong number!!! Write int number more 0 and less 100! Repeat enter:");
                else break;
            } catch (InputMismatchException e) {
                System.out.println("It isn't int number from 3 to 99!");
                return;
            }
        }

        field = createNewField(sizeField);
        fillField(field);

        showField(field);

        boolean gameIsFinish = false;
        boolean winIsFound = false;
        int currentPlayer = 1;
        char currentSign = 'x';
        scanner.nextLine();

        while (!winIsFound && !gameIsFinish) {

            showRequest(currentPlayer == 1 ? firstNameOfPlayer : secondNameOfPlayer);
            try {
                String getAnswer = scanner.nextLine();
                int[] pairRowColumn = getPairRowColumn(getAnswer);
                boolean isCorrectEnter = checkCorrectEnter(pairRowColumn, field);
                if (isCorrectEnter) {
                    pasteToField(pairRowColumn, field, currentPlayer);
                } else {
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please, check your data! It isn't number!");
                continue;
            }

            winIsFound = checkForWin(field, currentSign);
            gameIsFinish = checkFinishedGame(field);
            showField(field);

            if (winIsFound) {
                if (currentPlayer == 1) {
                    congratulatePlayer(tableOfWins, firstNameOfPlayer);
                } else {
                    congratulatePlayer(tableOfWins, secondNameOfPlayer);
                }

                boolean continuation = askAboutСontinuation();
                if (continuation) {
                    winIsFound = false;
                    field = createNewField(sizeField);
                    fillField(field);

                    showField(field);
                    continue;
                } else {
                    System.out.println("Game is finished!");
                    showTableOfWins(tableOfWins);
                    break;
                }
            }
            if (gameIsFinish) {
                System.out.println("Game over!");
                showTableOfWins(tableOfWins);
                boolean continuation = askAboutСontinuation();
                if (continuation) {
                    gameIsFinish = false;
                }
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;
            currentSign = currentPlayer == 1 ? 'x' : '0';

        }
        scanner.close();

    }

    private static void showTableOfWins(Map<String, Integer> tableOfWins) {

        System.out.println("*****************************");
        for (Map.Entry<String, Integer> entry : tableOfWins.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("* " + addSpace(key.trim(), 20) + "* " + addSpace(value.toString().trim(), 3) + "*");
            System.out.println("*****************************");
        }

    }

    private static String addSpace(String word, int lenghtString) {

        String retundWork = word;
        for (int i = 0; i < lenghtString - word.length(); i++) {
            retundWork = retundWork + " ";
        }

        return retundWork;
    }

    private static void congratulatePlayer(Map tableOfWins, String name) {

        System.out.println("Congratulations! Player " + name.toUpperCase() + " is win!");
        int currentCountOfWins = (int) tableOfWins.get(name);
        currentCountOfWins++;
        tableOfWins.put(name, currentCountOfWins);

    }

    private static String enterName(String numberOfPlayer) {
        System.out.println("Please, enter the name of " + numberOfPlayer + " player:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        //scanner.close();
        return name;
    }

    private static boolean askAboutСontinuation() {
        System.out.println("Would you repeat game? Answer Y/N");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            System.out.println(answer);

            if (answer.trim().equals("y") || answer.trim().equals("Y")) {
                return true;
            } else if (answer.trim().equals("n") || answer.trim().equals("N")) {
                return false;
            } else {
                System.out.println("Write Y or N!!!");
            }
        }
    }

    private static void fillField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '-';
            }
        }
    }

    private static char[][] createNewField(int size) {
        return new char[size][size];
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

    private static boolean checkCorrectEnter(int[] pairRowColumn, char[][] field) {

        int numberRow = pairRowColumn[0];
        int numberColumn = pairRowColumn[1];

        if (numberRow >= 1 && numberRow <= field.length && numberColumn >= 1 && numberColumn <= field.length) {
            if (field[numberRow - 1][numberColumn - 1] == '-') {
                return true;
            } else {
                System.out.println("This place is not empty!");
            }
        }
        System.out.println("You write wrong number row or column");
        return false;
    }

    private static int[] getPairRowColumn(String answer) {

        String[] pairRowColumn;
        pairRowColumn = answer.split(" ");
        if (pairRowColumn.length != 2) {
            return new int[]{-1, -1};
        }
        int[] rowAndColumn = {Integer.parseInt(pairRowColumn[0]), Integer.parseInt(pairRowColumn[1])};

        return rowAndColumn;
    }

    private static boolean checkForWin(char[][] fields, char currentSign) {

        boolean winRow = checkForWinRows(fields, currentSign == 'x' ? "0" : "x");
        if (winRow) {
            return true;
        }

        boolean winColumns = checkForWinColumns(fields, currentSign == 'x' ? "0" : "x");

        if (winColumns) {
            System.out.println(currentSign);
            return true;
        }
        boolean winDiagonals = checkForWinDiagonals(fields, currentSign == 'x' ? "0" : "x");

        if (winDiagonals) {
            return true;
        }

        return winDiagonals || winRow || winColumns;
    }

    private static boolean checkForWinDiagonals(char[][] fields, String opositeSign) {

        String mainDiag = "";
        String addDiag = "";
        for (int i = 0; i < fields.length; i++) {

            mainDiag = mainDiag + fields[i][i];
            addDiag = addDiag + fields[i][fields.length - i - 1];

        }

        if ((isNotCharacterInString(mainDiag, "-") && isNotCharacterInString(mainDiag, opositeSign))) {
            return true;
        }

        if ((isNotCharacterInString(addDiag, "-") && isNotCharacterInString(addDiag, opositeSign))) {
            return true;
        }

        return false;
    }

    private static boolean checkForWinColumns(char[][] fields, String opositeSign) {

        for (int i = 0; i < fields.length; i++) {
            String columnToString = "";
            for (int j = 0; j < fields[i].length; j++) {
                columnToString = columnToString + fields[j][i];
            }

            if (isNotCharacterInString(columnToString, "-") && isNotCharacterInString(columnToString, opositeSign)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkForWinRows(char[][] fields, String opositeSign) {

        for (int i = 0; i < fields.length; i++) {

            String currentRow = Arrays.toString(fields[i]);
            if (isNotCharacterInString(currentRow, "-") && isNotCharacterInString(currentRow, opositeSign)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isNotCharacterInString(String string, String character) {
        return !string.contains(character);
    }

    private static void pasteToField(int[] pairRowColumn, char[][] field, int numberOfPlayer) {

        int numberRow = pairRowColumn[0];
        int numberColumn = pairRowColumn[1];
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

    private static void showRequest(String name) {
        System.out.println("Player " + name.toUpperCase() + ", please, write firstly row, then column");
    }
}
