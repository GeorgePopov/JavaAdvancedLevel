package lesson2_exceptions.teacherExample;

public class MyArraySizeException extends IllegalArgumentException {

    public MyArraySizeException() {
        super("Invalid array size, requires 4x4");
    }
}