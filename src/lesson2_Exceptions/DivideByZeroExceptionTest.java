package lesson2_Exceptions;

public class DivideByZeroExceptionTest {
    public static void main(String[] args) {
        try {
            int a = 0;
            int b = divide(a);
            System.out.println(b);

        } catch (DivideByZeroException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Finish!");
    }

    private static int divide(int a) {
        if (a == 0) {
            throw new DivideByZeroException();
        }
        return 10 / a;
    }
}