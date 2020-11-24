package lesson2_Exceptions;

/**
 * Exception нужно выставлять по убыванию от наследников к более старшим классам
 * Exception срабатывет только первый, остальные в одном блоке try игнорируются
 */
public class ArithmeticTest {
    public static void main(String[] args) {

//        test();
//        test2();
        twoBlockCatch();
    }

    private static void test() {
        try {
            int a = 0;
            int b = divide(a);
            System.out.println(b);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("finish!");
    }

    private static void test2() {
        try {
            int a = 0;
            int b = 10 / a;
            System.out.println("Это сообщение не бужет выведено в консоль");
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        }
        System.out.println("Завершение работы");
    }

    private static void twoBlockCatch() {
        try {
            int a = 0;
            int b = divide(a);
            System.out.println(b);
            int[] array = {1, 2, 3};
            array[42] = 99;
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка индексации массива");
            System.out.println(e.getMessage());
        } /*catch (Exception e) { // можно еще добавить просто Exception
            e.printStackTrace();
        }*/
        /*catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {}*/ // можно так
        System.out.println("Завершение работы");
    }

    private static int divide(int a) {
        return 10 / a;
    }
}