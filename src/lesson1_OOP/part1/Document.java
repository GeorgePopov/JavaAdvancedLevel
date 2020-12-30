package lesson1_OOP.part1;

import java.util.Objects;

public class Document {
    public static final String DEFAULT_TITLE = "Unknown";

    private static int staticField = 5;

    private static void printMessage() {
        System.out.println(staticField);
    }

    private String title;
    private String content;

    int count;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Document(String content) {
        this(DEFAULT_TITLE, content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Document document = (Document) obj;
        return Objects.equals(title, document.title) && Objects.equals(content, document.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }

    public static void main(String[] args) {
        Document doc1 = new Document("title", "content document");
        Document doc2 = new Document("title", "content document");

        System.out.println(doc1 == doc2);
        System.out.println(doc1.equals(doc2));

        System.out.println(doc1.toString());

        Document doc3 = new Document("content");
        System.out.println(doc3.toString());
        Document.printMessage(); // static method coll
    }
}
