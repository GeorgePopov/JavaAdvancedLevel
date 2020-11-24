package lesson2_Exceptions.homework;

public class MyArraySizeException extends NegativeArraySizeException {

    public MyArraySizeException() {
        super("Creating of array an uncorrected size!");
    }
}
