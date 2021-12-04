import java.io.*;
import java.util.*;

public class FOLLOW {

    public static String input[][];
    public static String first[] = { "(d", "+ε", "(d", "*ε", "(d" };
    public static String follow[];
    public static int count, row, col;
    public static String temp;
    public static String nonterminal = "EMTNF";
    public static String terminal = "ε+*()d";
    public static void main(String[] args) throws Exception {
        String slash[];
        temp = new String();
        row = 10;
        col = 10;
        input = new String[row][col];
        count = 0;
        Scanner sf = new Scanner(new File("Input1.txt"));
        System.out.println("Given Grammar:");
        while (sf.hasNext()) {
            temp = sf.nextLine();
            System.out.println(temp);

            input[count][0] = temp.substring(0, 1);
            temp = temp.substring(3);
            slash = temp.split("/");
            for (int i = 0; i < slash.length; i++) {
                input[count][i + 1] = slash[i];
            }
            count++;
        }
        System.out.println("\nFollow Set:");
        follow = new String[count];

        for (int i = 0; i < count; i++) {

            temp = "";
            follow(i);
            System.out.println("FOLLOW(" + input[i][0] + ") = {" + temp + "}");
            follow[i] = temp;
        }
    }

    public static void follow(int pos) {
        int k;
        String left = input[pos][0];
        String term;
        if (pos == 0) {
            temp = temp + "$";
        }

        for (int i = 0; i < count; i++) {
            for (int j = 1; j < col; j++) {
                if (input[i][j] != null && input[i][j].contains(left)) {
                    k = input[i][j].indexOf(left) + 1;
                    term = input[i][j].substring(k);

                    if (terminal.contains(term)) {
                        temp = temp + term;
                    }

                    else if (nonterminal.contains(term)) {
                        for (int a = 0; a < count; a++) {
                            if (input[a][0].equals(term)) {
                                if (!temp.contains(first[a].replace("ε", ""))) {
                                    if (first[a].contains("ε")) {
                                        temp = temp + first[a].replace("ε", "");
                                        for (int x = 0; x < count; x++) {
                                            if (input[x][0].equals(input[i][0])) {
                                                follow(x);
                                                break;
                                            }
                                        }

                                    } else
                                        temp = temp + first[a];
                                    break;
                                }
                            }
                        }
                    }
                    if (k == input[i][j].length()) {
                        for (int a = 0; a < count; a++) {
                            if (input[a][0].equals(input[i][0]) && !input[i][0].equals(input[i][j].substring(k - 1))) {
                                follow(a);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}

// OUTPUT 1 :
//Given Grammar:
//EMTNF
//??µ+*()d

//Follow Set:
//FOLLOW(E) = {$}
//FOLLOW(?) = {}


// OUTPUT 2:
//Given Grammar:
//E->TN
//N->+TN/?µ
//T->FM
//M->*FM/?µ
//F->(E)/d

//Follow Set:
//FOLLOW(E) = {$)}
//FOLLOW(N) = {$)}
//FOLLOW(T) = {+$)}
//FOLLOW(M) = {+$)}
//FOLLOW(F) = {*+$)}
//