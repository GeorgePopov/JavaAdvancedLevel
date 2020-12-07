package lesson5_multithreading.thread;

public class ThreadPriority {
    static class MyRunnableClass2 implements Runnable {

        private int delayInMilli;

        public MyRunnableClass2(int delayInMilli) {
            this.delayInMilli = delayInMilli;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": new thread: " + i
                        + " with priority: " + Thread.currentThread().getPriority());
            }
        }

        public static void main(String[] args) {
            Thread thread1 = new Thread(new MyRunnableClass2(900), "Thread 1");
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

            thread1.setPriority(1); // priority from 1 to 10, default(NORMAL) = 5;
            thread1.start();

            // lambda expression
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": new thread: " + i
                            + " with priority: " + Thread.currentThread().getPriority());
                }
            }, "Thread 2");
            thread2.start(); // потоку 2 присвоился приоритет главного потока т.к. ему мы не задали а 1 потоку задали
        }
    }
}