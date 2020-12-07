package lesson5_multithreading.thread;

/**
 * Атомарная операция — операция, которая либо выполняется целиком, либо не выполняется вовсе;
 * операция, которая не может быть частично выполнена и частично не выполнена.
 * У каждого объекта в java есть монитор.
 */
public class ThreadExample {
    private static int count = 0; // разделяемый ресурс на два потока

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
//        printCount(); // вызываем метод поочерёдно в одном потоке
//        printCount();

        Thread thread1 = new Thread(() -> printCount(), "T1"); // вызываем метод паралельно в разных потоках
        Thread thread2 = new Thread(() -> printCount(), "T2");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final value is " + count);
    }

    private static void printCount() {
        for (int i = 0; i < 100; i++) {
//            count++;

            synchronized (ThreadExample.class) {
                count = count + 1;
            }

//            System.out.println(Thread.currentThread().getName() + ": " + count++); // подробно все итерации

            try {
                Thread.sleep(10); // задержка при каждом выполнении цикла даёт результат
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}