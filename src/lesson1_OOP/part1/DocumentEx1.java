package lesson1_OOP.part1;

public class DocumentEx1 {
    String title;
    String content;

    public static void main(String[] args) {
        DocumentEx1 document1 = new DocumentEx1();
        document1.title = "My first part1";
        document1.content = "Content of my first part1";

        System.out.println(document1.title);
        System.out.println(document1.content);
    }
}
