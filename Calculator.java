import java.util.*;



public class Calculator {


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
                num1 = convertToInt(tokens[0]);
                num2 = convertToInt(tokens[2]);
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
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверная арифметическая операция");
        }

        if (isRoman) {
            return convertToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRomanNumeral(String input) {
        return input.matches("[IVXLCDM]+");
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