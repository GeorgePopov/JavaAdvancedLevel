package lesson5_multithreading.thread;

/**
 * Потоки останавливать необходимо только с закрытием всех
 * ресурсов использующихся, открытых в этом потоке.
 */
public class ThreadInterrupt {
    static class MyRunnableClass3 implements Runnable {

        private int delayInMilli;

        public MyRunnableClass3(int delayInMilli) {
            this.delayInMilli = delayInMilli;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

//                try {
                // поток можно прервать и когда он спит, будет Exception, его можно обработать в блоке catch()
//                    Thread.sleep(100);

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread has been interrupted");
                        return;
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + i
                            + " with priority: " + Thread.currentThread().getPriority());

//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread thread1 = new Thread(new MyRunnableClass3(10), "Thread 1");
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

            while (thread2.isAlive()) { // метод ты там живой?
                Thread.sleep(10);
            }
            System.out.println("the end");
        }
    }
}