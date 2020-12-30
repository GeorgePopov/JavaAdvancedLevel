package lesson1_OOP.part2;

public enum Color {

//    BLACK,
//    WHITE,
//    RED,
//    GRAY;

    BLACK("Чёрный"),
    WHITE("Белый"),
    RED("Красный"),
    GRAY("Серый");

    private String russianColor;

    Color(String russianColor) {
        this.russianColor = russianColor;
    }

    public String getRussianColor() {
        return russianColor;
    }
}