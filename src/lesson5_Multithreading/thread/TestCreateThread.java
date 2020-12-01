package lesson5_Multithreading.thread;

/**
 * Работа с методом sleep().
 */
public class TestCreateThread {

    static class MyRunnableClass implements Runnable {

        private int delayInMilli;

        public MyRunnableClass(int delayInMilli) {
            this.delayInMilli = delayInMilli;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(delayInMilli);
                    System.out.println(Thread.currentThread().getName() + ": new thread: " + i);
//
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("Interrupted from code");
//                        return;
//                    }

                } catch (InterruptedException e) {
                    System.out.println("Interrupted from exception");
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            // создание нового потока с помощью создание нового класса имплементируемого интерфейс Runnable
            // если не задавать имя потока, то оно будет сгенерированно по умолчанию, например Thread-0
            Thread thread1 = new Thread(new MyRunnableClass(900), "Thread 1");
            thread1.start();

            // generating code in a new thread using the runnable interface
//            Thread thread2 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 10; i++) {
//                        System.out.println(Thread.currentThread().getName() + ": new thread: " + i);
//                    }
//                }
//            }, "Thread 2");
//            thread2.start();

            // lambda expression
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }, "Thread 2");
            thread2.start();
        }
    }
}