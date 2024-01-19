import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[]{100, 1, 111, 11, 5};
        System.out.println("Choose: 1 - max, 2 - min");
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        if (result == 1) {
            int maxNumber = findMaxNumber(numbers);
            writeResult(maxNumber, "max");
        } else if (result == 2) {
            int minNumber = findMinNumber(numbers);
            writeResult(minNumber, "min");
        } else {
            System.out.println("You write wrong date");
        }
    }

    private static void writeResult(int result, String addText) {
        System.out.println(addText + " number is " + result);
    }

    private static int findMaxNumber(int[] numbers) {
        int maxNumber = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > maxNumber) {
                maxNumber = numbers[i];
            }
        }
        return maxNumber;
    }

    private static int findMinNumber(int[] numbers) {
        int minNumber = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < minNumber) {
                minNumber = numbers[i];
            }
        }
        return minNumber;
    }
}