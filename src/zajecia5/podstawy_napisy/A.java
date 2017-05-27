package zajecia5.podstawy_napisy;

/**
 * Created by pm on 2017-04-22.
 */
public class A {
    public static void main(String[] args) {
        String w = "abra kadaba";

        System.out.println(w);

        System.out.println(w + w);

        System.out.println(w.startsWith("abra"));
        System.out.println(w.startsWith("xbra"));

        System.out.println(w.replace('a','.'));

        String krotki = w.substring(2);
        System.out.println("---->" + krotki);

    }
}
