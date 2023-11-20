import java.util.*;

public class Calculator {

    private static final String[] ROMAN_SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] ROMAN_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private static final Map<Character, Integer> ROMAN_MAP = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public static String calculate(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Некорректное количество аргументов");
        }

        boolean isRoman = isRomanNumeral(tokens[0]) && isRomanNumeral(tokens[2]);

        int num1;
        int num2;

        try {
            if (isRoman) {
                num1 = convertToArabic(tokens[0]);
                num2 = convertToArabic(tokens[2]);
            } else {
                num1 = Integer.parseInt(tokens[0]);
                num2 = Integer.parseInt(tokens[2]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Должны быть только целые числа");
        }

        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
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
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверная арифметическая операция");
        }

        return isRoman ? convertToRoman(result) : String.valueOf(result);
    }

    private static boolean isRomanNumeral(String input) {
        return input.matches("[IVXLCDM]+");
    }

    private static int convertToArabic(String romanNum) {
        int arabianNum = 0;
        int prevValue = 0;

        for (int i = romanNum.length() - 1; i >= 0; i--) {
            int currentValue = ROMAN_MAP.get(romanNum.charAt(i));
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

        StringBuilder romanNum = new StringBuilder();
        int i = 0;
        while (arabianNum > 0) {
            if (arabianNum - ROMAN_VALUES[i] >= 0) {
                romanNum.append(ROMAN_SYMBOLS[i]);
                arabianNum -= ROMAN_VALUES[i];
            } else {
                i++;
            }
        }
        return romanNum.toString();
    }
}