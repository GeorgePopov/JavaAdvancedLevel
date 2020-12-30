package lesson1_OOP.trainingManual;

public class CatEx2 {
    private String name;
    private int age;

    public CatEx2(String name) {
        this.name = name;
    }

    public CatEx2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age * 71;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CatEx2 another = (CatEx2) o;
        return this.age == another.age && this.name.equals(another.name);
    }

    public static void main(String[] args) {
        CatEx2 catEx2 = new CatEx2("Sam");
        System.out.println(catEx2);
        System.out.println(catEx2.hashCode()); // непонятно с этим hashCode()

        CatEx2 cat1 = new CatEx2("Rob", 5);
        CatEx2 cat2 = new CatEx2("Rob", 5);


        System.out.println(cat1.equals(cat2));



    }
}