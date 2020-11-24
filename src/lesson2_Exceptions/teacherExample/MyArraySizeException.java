package lesson2_Exceptions.teacherExample;

public class MyArraySizeException extends IllegalArgumentException {

    public MyArraySizeException() {
        super("Invalid array size, requires 4x4");
    }
}