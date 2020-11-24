package lesson2_Exceptions;

public class DivideByZeroException extends ArithmeticException{

    public DivideByZeroException() {
        super("Произошло деление на ноль!");
    }
}