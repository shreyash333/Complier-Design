import java.io.*;
import java.util.*;

public class CodeOpti {
    public static void main(String args[]) throws Exception {
        ArrayList a = new ArrayList();
        Scanner sc = new Scanner(new File("Input.txt"));
        String temp;
        while (sc.hasNext()) {
            temp = sc.nextLine().substring(3);
            if (!a.contains(temp)) {
                a.add(temp);
            }
        }
        System.out.println("Optimized Code:");
        for (int i = 0; i < a.size(); i++) {
            System.out.println("t" + (i + 1) + " = " + a.get(i));
        }
    }
}