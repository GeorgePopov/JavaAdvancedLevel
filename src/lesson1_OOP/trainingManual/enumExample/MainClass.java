package lesson1_OOP.trainingManual.enumExample;

public class MainClass {
    public static void main(String[] args) {
        Fruit fruit = Fruit.APPLE;
        System.out.println(fruit);

        if (fruit == Fruit.APPLE) {
            System.out.println("WOW");
        }

        switch (fruit) {
            case APPLE:
                System.out.println("Яблоко");
                break;
            case ORANGE:
                System.out.println("Апельсин");
                break;
            case BANANA:
                System.out.println("Банан");
                break;
            case CHERY:
                System.out.println("Вишня");
                break;
        }

        System.out.println("Все элементы перечесления:");
        for (Fruit fruitArr : Fruit.values()) {
            System.out.println(fruitArr);
        }

        System.out.println("Поиск по названию: " + Fruit.valueOf("BANANA"));
    }
}