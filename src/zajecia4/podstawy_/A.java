package zajecia4.podstawy_;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A {
    public static void main(String[] args) {
        List<String> produkty = new ArrayList<>();

        produkty.add("Woda");
        produkty.add("Cukier");
        produkty.add("Drożdże");

        System.out.println(produkty);
        System.out.println(produkty.get(2));
        produkty.set(1, "Mleko");
        System.out.println(produkty);

        for(String s : produkty) {
            System.out.println("<<" + s + ">>");
        }

        Collections.sort(produkty);
        System.out.println(produkty);
    }
}
