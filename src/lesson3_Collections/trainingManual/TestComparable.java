package lesson3_collections.trainingManual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestComparable {
    public static void main(String[] args) {

        List<CatComparable> catList = new ArrayList<>(Arrays.asList(new CatComparable("A", 2),
                new CatComparable("B", 3), new CatComparable("C", 1)));

        System.out.println(catList);
        Collections.sort(catList);
        System.out.println(catList);

    }
}
