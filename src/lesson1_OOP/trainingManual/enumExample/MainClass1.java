package lesson1_OOP.trainingManual.enumExample;

public class MainClass1{
    public static void main(String[] args) {

        for (Fruit1 fruit1 : Fruit1.values()) {
            System.out.printf("Средний вес фрукта %s, составляет %d кг, \n",
                    fruit1.getRussianTitle(), fruit1.getWeight());
        }
    }
}