package lesson1_OOP.trainingManual;

/**
 * Пример как можно воспользоваться методом реализованным только в подклассе,
 * но при этом переменная объекта подкласса иммет тип данных суперкласса,
 * следовательно метод реализованный только в подклассе не виддно нашей переменной.
 * Нужно сделать явное приведение типа (каст), а потом вызвать метод будет можно.
 */
public class ExampleEx1 {
    public static void main(String[] args) {
        AnimalEx1 animalEx1 = new CatEx1();
        animalEx1.voice();

        if (animalEx1 instanceof CatEx1) {
            ((CatEx1)animalEx1).methodFromCatClass();
            System.out.println("В animalEx1 действительно лежит Cat");
        }
        ((CatEx1)animalEx1).methodFromCatClass(); // упрощенная форма
    }
}