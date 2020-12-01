package lesson5_Multithreading.thread;

/**
 * Наиболее редко используемый вариант создания нового потока путём наследования от класса Thread.
 * Его стоит использовать, если нужно переопределить методы из класса Thread.
 */
public class TestCreateThread2 {

    static class MyThread extends Thread { // static - для метода main !
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                    System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); // так можно узнать в каком потоке выводится sout
        new MyThread().start();
    }
}