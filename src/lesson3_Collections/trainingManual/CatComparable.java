package lesson3_collections.trainingManual;

public class CatComparable implements Comparable {

    String name;
    int age;

    public CatComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        CatComparable another = (CatComparable) o;
        if (this.age > another.age) {
            return 1;
        }
        if (this.age < another.age) {
            return -1;
        }
        return 0;

//        return this.age - ((CatComparable)o).age; // сокращенно можно так
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
