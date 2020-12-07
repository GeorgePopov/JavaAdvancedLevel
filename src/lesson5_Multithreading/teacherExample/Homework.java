package lesson5_multithreading.teacherExample;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// Очень интересный пример, изучить и попробовать переписать!
public class Homework {
    static final int SIZE = 10000000;
    static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        // !*!*! методы sequentialMethod() и parallelMethod() являются параметрами
        // для метода measureTime() так можно делать используя лямбда выражения

        float[] data1 = createArray(SIZE);
        measureTime(() -> sequentialMethod(data1), "sequentialMethod");
        // развёрнутый пример, всё происходит в аргументе метода
//        measureTime(new Runnable() {
//            @Override
//            public void run() {
//                sequentialMethod(data1);
//            }
//        }, "sequentialMethod");

        float[] data2 = createArray(SIZE);
        measureTime(() -> parallelMethod(data2), "parallelMethod");

        System.out.println("Arrays are equal: " + Arrays.equals(data1, data2));
    }

    private static void sequentialMethod(float[] data) {
        sequentialMethod(data, 0);
    }

    private static void sequentialMethod(float[] data, int offset) {
        for (int index = 0; index < data.length; index++) {
            float currentValue = data[index];
            data[index] = computeValue(index + offset, currentValue);
        }
    }

    private static void parallelMethod(float[] data) {
        float[] part1 = Arrays.copyOfRange(data, 0, HALF_SIZE);
        float[] part2 = Arrays.copyOfRange(data, HALF_SIZE, data.length);

        Thread thread1 = new Thread(() -> sequentialMethod(part1, 0));
        Thread thread2 = new Thread(() -> sequentialMethod(part2, HALF_SIZE));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Выполнение прервано!");
            e.printStackTrace();
            return;
        }

        System.arraycopy(part1, 0, data, 0, HALF_SIZE);
        System.arraycopy(part2, 0, data, HALF_SIZE, HALF_SIZE);
    }

    private static float[] createArray(int size) {
        float[] data = new float[size];
        Arrays.fill(data, 1.0f); // по идее можно просто 1, она сама преобразуется в значение флоат
        return data;
    }

    private static float computeValue(int index, float currentValue) {
        return (float)(currentValue * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
    }

    // !*!*! ТАК МОЖНО ИСПОЛЬЗОВАТЬ Runnable, БРАТЬ В ПАРАМЕТРЫ И ЗАПУСКАТЬ МЕТОД run(), ИСПОЛЬЗОВАТЬ С ЛЯМБДОЙ ↑
    private static void measureTime(Runnable action, String actionName) {
        long start = System.nanoTime(); // замеряем начало, до выполнения метода
        action.run();
        long finish = System.nanoTime();
        long duration = finish - start;
        // преобразовываем наносекунды в милисекунды TimeUnit.NANOSECONDS.toMillis()
        // класс TimeUnit может делать много разных преобразований, изучить!
        System.out.printf("Method '%s' took %d ms%n", actionName, TimeUnit.NANOSECONDS.toMillis(duration));
    }
}