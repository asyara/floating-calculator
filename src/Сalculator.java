import exception.ValidationException;
import operations.Operations;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Сalculator {

    private static Operations action = new Operations();
    private static final String REGEX_1 = "(-?[0-9]*[.]?[0-9]+)(/|\\*)(-?[0-9]*[.]?[0-9]+)";
    private static final String REGEX_2 = "(-?[0-9]*[.]?[0-9]+)(\\+|-)(-?[0-9]*[.]?[0-9]+)";
    private static final String REGEX_3 = "[\\d.\\-/*+]*";
    private static final String REGEX_4 = "-?[0-9]*[.]?[0-9]+";


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Please, type expression and press Enter: ");
        while (sc.hasNext()) {
            String parseStr = sc.nextLine();
            try {
                if (parseStr.equals("q")) {
                    System.exit(1);
                }
                parseStr = normalizeString(parseStr);
                parseStr = calculate(calculate(parseStr, REGEX_1), REGEX_2);
                isValidAnswer(parseStr);
                System.out.println(parseStr);

            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Please, type expression or press \"q\" for Exit: ");
        }
    }

    public static String calculate(String parseStr, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(parseStr);
        while (m.find()) {
            String expression = operationToString(m.group(1), m.group(2), m.group(3));
            double firstNum = Double.parseDouble(m.group(1));
            String sign = m.group(2);
            double secondNum = Double.parseDouble(m.group(3));
            Double result = action.operation(firstNum, secondNum, sign);
            parseStr = replaceExpression(parseStr, expression, result.toString());
            m = pattern.matcher(parseStr);
        }
        return parseStr;
    }

    private static String operationToString(String num, String sign, String num2) {
        return num + sign + num2;
    }

    public static String replaceExpression(String parseStr, String expression, String result) {
        parseStr = parseStr.replace(expression, result);
        return parseStr;
    }

    public static String normalizeString(String parseStr) throws ValidationException {
        String result = parseStr.replaceAll(" ", "");
        isValid(result);
        return result;
    }

    public static void isValid(String parseStr) throws ValidationException {
        if (!parseStr.matches(REGEX_3)) {
            throw new ValidationException("Invalid expression");
        }
    }

    public static void isValidAnswer(String parseStr) throws ValidationException {
        if (!parseStr.matches(REGEX_4)) {
            throw new ValidationException("Invalid expression");
        }
    }
}