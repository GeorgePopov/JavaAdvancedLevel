package lesson1_OOP.part1;

public class DocumentEx2 {
    String title;
    String content;

    void printInfo() {
        System.out.println(title + System.lineSeparator() + content); // LineSeparator.Windows or \n
    }

    public static void main(String[] args) {
        int a = 5; // in cell value stored - 005

        DocumentEx2 document1 = new DocumentEx2(); // cell - 001 link - [112]
        document1.title = "document1";
        document1.content = "content document1";
        document1.printInfo();

        DocumentEx2 document2 = new DocumentEx2(); // 002[555]
        document2.title = "document2";
        document2.content = "content document2";
        document2.printInfo();

//        !*!*! 0:37 how to compare String objects

//        document1 == document2 // 112 == 555
//        document1.equals(document2) // need to override the method
//        document1 = document2 001[555] and 002[005]
    }
}
