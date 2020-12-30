package lesson1_OOP.trainingManual;

public class Outer {

    class Inner {
        private int innerVar;

        public Inner(int innerVar) {
            this.innerVar = innerVar;
        }

        void innerTest() {
            System.out.println("innerVar: " + innerVar);
            System.out.println("outerVar: " + outerVar);
        }
    }

    private int outerVar;

    public Outer(int outerVar) {
        this.outerVar = outerVar;
    }

    public void outerTest() {
        System.out.println("outerVar: " + outerVar);
//        System.out.println("innerVar: " + innerVar); // ошибка

        Inner io = new Inner(20);
        System.out.println("io.innerVar = " + io.innerVar);
    }

    public static void main(String[] args) {
        Outer outer = new Outer(10);
        outer.outerTest();
    }
}