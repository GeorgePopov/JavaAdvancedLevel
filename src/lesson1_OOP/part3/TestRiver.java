package lesson1_OOP.part3;

public class TestRiver {
    public static void main(String[] args) {
        River river = new River("Don", 100);

        Waterfowl duck = new Duck("Donald");
        DogV2 dog = new DogV2("Rob", "Black", "Boxer");
        Pet cat = new CatV2("Bob", "white");
        System.out.println(river.doSwim(duck));
        System.out.println(river.doSwim(dog)); // ! dog принимает потому, что он имплементирует интерфейс Waterfowl

        Waterfowl[] waterfowls = {duck, dog};
        swim(river, waterfowls);
        swim(river, duck, dog);
    }

    private static void swim(River river, Waterfowl... waterfowls) { // ! варируемое колличество аргументов
        for (Waterfowl waterfowl : waterfowls) {
            System.out.println(waterfowl);
            System.out.println(river.doSwim(waterfowl));
        }
    }
}