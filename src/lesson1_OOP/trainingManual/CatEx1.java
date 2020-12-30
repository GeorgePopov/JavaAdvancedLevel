package lesson1_OOP.trainingManual;

public class CatEx1 extends AnimalEx1{

    void voice() {
        super.voice();
        System.out.println("Кот мяукнул");
    }

    void methodFromCatClass() {
        System.out.println("methodFromCatClass");
    }
}