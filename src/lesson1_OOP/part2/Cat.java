package lesson1_OOP.part2;

public class Cat extends Animal {
    private Color color = Color.RED;
    private CatAttribute catAttribute;

    public static class CatAttribute { // внутренний класс, эти атрибуты не имею смысла без старшего класса
        private String mael;
        private int weight;
        private Color colorEyes;
    }

    public Cat(String name, Color color, CatAttribute catAttribute) {
        super(name);
        this.catAttribute = catAttribute;
        this.color = color;
    }

    @Override
    public void animalInfo() {
        super.animalInfo(); // call the parent implementation animalInfo()
        System.out.println("Cat name is " + super.getName() + "; color - " + color);
        // т.к. своего поля name у класса Cat нет, мы через super и getName() обращаемся к полю name родителя класса AnimalEx1
    }

    public CatAttribute getCatAttribute() {
        return catAttribute;
    }

    @Override
    public void voice() {
        System.out.println("May");
    }
}