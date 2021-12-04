import java.util.*;

class TAC {
    public static void main(String args[]) throws Exception {
        String input = "a=b*c+d*e";
        ArrayList s = new ArrayList();
        int ctr = 1;

        for (int i = 0; i < input.length(); i++) {
            s.add(input.substring(i, i + 1));
        }

        System.out.println("Input:");
        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i));
        }
        System.out.println("\n\nTAC:");
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).equals("*")) {
                s.set(i, "t" + ctr);
                System.out.println(s.get(i) + "=" + s.get(i - 1) + "*" + s.get(i + 1));
                s.remove(i + 1);
                s.remove(i - 1);
                ctr++;
                i--;
            }
        }

        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).equals("+")) {
                s.set(i, "t" + ctr);
                System.out.println(s.get(i) + "=" + s.get(i - 1) + "+" + s.get(i + 1));
                s.remove(i + 1);
                s.remove(i - 1);
                ctr++;
                i--;
            }
        }

        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i));
        }
    }
}
