package lesson2_exceptions;

public class DivideByZeroException extends ArithmeticException{

    public DivideByZeroException() {
        super("Произошло деление на ноль!");
    }
}