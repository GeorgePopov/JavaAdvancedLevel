package lesson3_Collections.trainingManual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {

        // Arrays.asList() так добавлять элементы в коллекцию ?
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "C", "A", "A", "B", "C", "B"));

        Iterator<String> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals("A")) {
                iterator.remove();
            }
        }
    }
}
