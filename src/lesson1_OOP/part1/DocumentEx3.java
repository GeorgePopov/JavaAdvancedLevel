package lesson1_OOP.part1;

public class DocumentEx3 {
    String title;
    String content;

    public DocumentEx3(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public DocumentEx3() {}

    void printInfo() {
        System.out.println(title + System.lineSeparator() + content);
    }

    public static void main(String[] args) {
        new DocumentEx3("title1", "content document1").printInfo();
        new DocumentEx3("title2", "content document2").printInfo();
    }
}
