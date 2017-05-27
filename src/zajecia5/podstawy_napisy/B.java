package zajecia5.podstawy_napisy;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);




        int poz = 0;
        int leftBorder = -5, righBorder = 5;
        char c = '1';
        while(c!='x') {
            System.out.print("Twój ruch? (r,l,x):");
            c = s.next().charAt(0);
            if (c=='l') poz--;
            if (c=='r') poz++;
            System.out.println("Pozycja=" + poz);
        }

    }
}
