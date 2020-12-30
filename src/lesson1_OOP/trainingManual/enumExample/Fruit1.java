package lesson1_OOP.trainingManual.enumExample;

public enum Fruit1 {

    ORANGE("Апельсин", 1), APPLE("Яблоко", 2),
    BANANA("Банан", 3), CHERY("Вишня", 4);

    private String russianTitle;
    private int weight;

    public String getRussianTitle() {
        return russianTitle;
    }

    public int getWeight() {
        return weight;
    }

    Fruit1(String russianTitle, int weight) {
        this.russianTitle = russianTitle;
        this.weight = weight;
    }
}