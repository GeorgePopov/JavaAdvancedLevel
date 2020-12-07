package lesson2_exceptions.homework;

public class MyArraySizeException extends NegativeArraySizeException {

    public MyArraySizeException() {
        super("Creating of array an uncorrected size!");
    }
}
