package operations;

public class Division implements Operation {

    public double operation(double firstNum, double secondNum) {
        if (secondNum == 0) {
            throw new ArithmeticException("Divide by zero!");
        }
        double result = firstNum / secondNum;
        return result;
    }
}
