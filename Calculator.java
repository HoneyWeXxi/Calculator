import java.util.*;

class Calculator {
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
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверное количество аргументов");
        }

        int num1;
        int num2;

        try {
            num1 = convertToInt(tokens[0]);
            num2 = convertToInt(tokens[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверные числа");
        }

        String operator = tokens[1];

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверная арифметическая операция");
        }

        return convertToRoman(result);
    }

    private static int convertToInt(String romanNum) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int arabianNum = 0;
        int prevValue = 0;

        for (int i = romanNum.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(romanNum.charAt(i));
            if (currentValue < prevValue) {
                arabianNum -= currentValue;
            } else {
                arabianNum += currentValue;
            }
            prevValue = currentValue;
        }
        return arabianNum;
    }

    private static String convertToRoman(int arabianNum) {
        if (arabianNum < 1) {
            throw new IllegalArgumentException("Римские числа не могут быть отрицательными");
        }
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder romanNum = new StringBuilder();
        int i = 0;
        while (arabianNum > 0) {
            if (arabianNum - romanValues[i] >= 0) {
                romanNum.append(romanSymbols[i]);
                arabianNum -= romanValues[i];
            } else {
                i++;
            }
        }
        return romanNum.toString();
    }
}