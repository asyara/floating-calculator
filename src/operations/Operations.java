package operations;

public class Operations {

    private Multiplication multiplication = new Multiplication();
    private Division division = new Division();
    private Addition addition = new Addition();
    private Substraction substraction = new Substraction();

    public double operation(double a, double b, String operator) {
        switch (operator) {
            case "*":
                return multiplication.operation(a, b);
            case "/":
                return division.operation(a, b);
            case "+":
                return addition.operation(a, b);
            case "-":
                return substraction.operation(a, b);

            default:
                return 0;
        }
    }
}
