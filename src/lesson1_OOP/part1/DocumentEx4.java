package lesson1_OOP.part1;

public class DocumentEx4 {
    String title;
    String content;

    public DocumentEx4(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // ! в этом примере поле title автоматически не задаётся
    public DocumentEx4(String content) {
        this("Unknown", content); // ! link on constructor ↑
    }

    void printInfo() {
        System.out.println(this.title + System.lineSeparator() + this.content);
    }

    public static void main(String[] args) {
        DocumentEx4 document1 = new DocumentEx4("title1", "content document1");
        document1.printInfo();

        DocumentEx4 document2 = new DocumentEx4("content document2");
        document2.printInfo();
    }
}
