package lesson5_multithreading.synchronize;

public class Example_SB_1 {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();

        System.out.println("Start");

        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
    }

    public synchronized void method1() { // можно так, лучше так
        System.out.println("start M1");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end M1");
    }

    public void method2() {
        // можно так, так лучше если хотим синхронизировать определённый участок кода, а не весь метод !
        synchronized (this) {
            System.out.println("start M2");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("end M2");
        }
    }
}