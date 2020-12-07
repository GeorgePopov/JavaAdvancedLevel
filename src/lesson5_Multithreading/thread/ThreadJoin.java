package lesson5_multithreading.thread;

public class ThreadJoin {
    static class MyRunnableClass4 implements Runnable {

        private int delayInMilli;

        public MyRunnableClass4(int delayInMilli) {
            this.delayInMilli = delayInMilli;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                try {
//                 поток можно прервать и когда он спит, будет Exception, его можно обработать в блоке catch()
                    Thread.sleep(100);

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread has been interrupted");
                    return;
                }

                System.out.println(Thread.currentThread().getName() + ": " + i
                        + " with priority: " + Thread.currentThread().getPriority());

                } catch (InterruptedException e) {
                    System.out.println("Thread has been interrupted");
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread thread1 = new Thread(new MyRunnableClass4(10), "Thread 1");
            thread1.setPriority(7);
            thread1.start();

            thread1.interrupt();
            System.out.println("finish");


            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }, "Thread 2");
            thread2.start();

            // про join() не совсем понятно !
            thread2.join();
            System.out.println("the end");
        }
    }
}