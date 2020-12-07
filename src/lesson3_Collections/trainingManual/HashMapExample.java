package lesson3_collections.trainingManual;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {

        HashMap<String, String> hm = new HashMap<>();

        hm.put("Russia", "Moscow");
        hm.put("France", "Paris");
        hm.put("Germany", "Berlin");
        hm.put("Norway", "Oslo");

        for (Map.Entry<String, String> o : hm.entrySet()) {
            System.out.println(o.getKey() + ": " + o.getValue());
        }

        hm.put("Germany", "Munich");
        System.out.println("New Germany Entry: " + hm.get("Germany"));
    }
}
