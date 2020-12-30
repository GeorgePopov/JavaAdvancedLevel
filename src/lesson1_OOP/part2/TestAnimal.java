package lesson1_OOP.part2;

public class TestAnimal {
    public static void main(String[] args) {

        for (Color value : Color.values()) {
            System.out.println(value.getRussianColor());
            System.out.println(value);
        }

        Cat.CatAttribute catAttribute = new Cat.CatAttribute(); // экземпляр внутреннего класса
        Animal cat = new Cat("Sam", Color.WHITE, catAttribute);
        Animal dog = new Dog("Dick", "black", "Boxer");

        cat.animalInfo();
        dog.animalInfo();

        ((Cat) cat).getCatAttribute(); // явное приведение типов
        System.out.println("instanceof: " + (cat instanceof Animal));
    }
}