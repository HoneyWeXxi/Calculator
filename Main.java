import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 3 + 4): ");
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }

    public static String calc(String input) {
        return Calculator.calculate(input);
    }
}
