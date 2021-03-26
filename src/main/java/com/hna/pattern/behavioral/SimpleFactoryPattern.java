package com.hna.pattern.behavioral;

public class SimpleFactoryPattern {

    public static void main(String[] args) {
        double x = 12;
        double y = 2;
        String cal = "-";
        double result = CalculatorFactory.calculate(x, y , cal);
        System.err.println(result);
    }

}

abstract class CalculatorFactory {

    public static double calculate(double x, double y, String cal) {
        Calculator calculator = null;
        switch (cal) {
            case "+":
                calculator = new AddCalculator(x, y);
                break;
            case "-":
                calculator = new SubCalculator(x, y);
                break;
            case "*":
                calculator = new MultiCalculator(x, y);
                break;
            case "/":
                calculator = new DivCalculator(x, y);
                break;
            default:
                throw new RuntimeException("not support this calculator");
        }
        return calculator.getResult();
    }
}

abstract class Calculator {

    protected double x;
    protected double y;

    public Calculator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    abstract double getResult();
}

class AddCalculator extends Calculator {

    public AddCalculator(double x, double y) {
        super(x, y);
    }

    @Override
    double getResult() {
        return x + y;
    }
}

class SubCalculator extends Calculator {

    public SubCalculator(double x, double y) {
        super(x, y);
    }

    @Override
    double getResult() {
        return x - y;
    }
}

class MultiCalculator extends Calculator {

    public MultiCalculator(double x, double y) {
        super(x, y);
    }

    @Override
    double getResult() {
        return x * y;
    }
}

class DivCalculator extends Calculator {

    public DivCalculator(double x, double y) {
        super(x, y);
    }

    @Override
    double getResult() {
        if (y == 0) {
            throw new IllegalArgumentException();
        }
        return x / y;
    }
}