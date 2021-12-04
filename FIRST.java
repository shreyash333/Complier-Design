import java.io.*;
import java.util.*;

public class FIRST {
    public static String input[][];
    public static String first[];
    public static int count, row, col;
    public static String temp;
    public static String nonterminal = "EMTNF";
    public static String terminal = "ε+*()d";

    public static void main(String[] args) throws Exception {
        String rs[];
        row = 10;
        col = 10;
        count = 0;
        input = new String[row][col];
        Scanner sf = new Scanner(new File("Input1.txt"));
        System.out.println("Given Grammar:");
        while (sf.hasNext()) {
            temp = sf.nextLine();
            System.out.println(temp);
            input[count][0] = temp.substring(0, 1);
            temp = temp.substring(3);
            rs = temp.split("/");
            for (int i = 0; i < rs.length; i++) {
                input[count][i + 1] = rs[i];
            }
            count++;
        }
        System.out.println("\nFirst Set:");
        first = new String[count];
        for (int i = 0; i < first.length; i++) {
            temp = "";
            first(i);
            System.out.println("FIRST(" + input[i][0] + ") = {" + temp + "}");
            first[i] = temp;
        }
    }

    public static void first(int pos) {
        String term;
        for (int i = 1; i < col; i++) {
            if (input[pos][i] != null) {
                term = input[pos][i].substring(0, 1);
                if (terminal.contains(term)) {
                    temp = temp + term;
                } else {
                    for (int j = 0; j < count; j++) {
                        if (input[j][0].equals(term)) {
                            first(j);
                            break;
                        }
                    }
                }
            }
        }
    }
}

// OUTPUT 1:
//Given Grammar:
//EMTNF
//?µ+*()d

//First Set:
//FIRST(E) = {}
//FIRST(?) = {*}


// OUTPUT 2:
//Given Grammar:
//E->TN
//N->+TN/?µ
//T->FM
//M->*FM/?µ
//F->(E)/d

//First Set:
//FIRST(E) = {(d}
//FIRST(N) = {+?}
//FIRST(T) = {(d}
//FIRST(M) = {*?}
//FIRST(F) = {(d}
//