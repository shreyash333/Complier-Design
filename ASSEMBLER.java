import java.io.*;
import java.util.*;

public class ASSEMBLER {
    public static int lc, p, q;
    public static FileWriter fw;
    public static String MOT[][] = { { "L", "01011000", "04", "RX" }, { "A", "01011010", "04", "RX" },
            { "ST", "01010000", "04", "RX" }, };
    public static String POT[][] = { { "START", "PA_START" }, { "USING", "PA_USING" }, { "DC", "PA_DC" },
            { "DS", "PA_DS" }, { "END", "PA_END" }, };
    public static String ST[][] = new String[5][4];
    public static String LT[][] = new String[5][4];
    public static String BT[][] = { { "01", "N", "-" }, { "02", "N", "-" }, { "03", "N", "-" }, { "04", "N", "-" },
            { "05", "N", "-" }, { "06", "N", "-" }, { "07", "N", "-" }, { "08", "N", "-" }, { "09", "N", "-" },
            { "10", "N", "-" }, { "11", "N", "-" }, { "12", "N", "-" }, { "13", "N", "-" }, { "14", "N", "-" },
            { "15", "N", "-" }, };

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("Input.txt"));
        fw = new FileWriter(new File("Output.txt"));
        String ins[];

        fw.write("Pass 1" + System.getProperty("line.separator"));
        fw.write("Address\tInstruction" + System.getProperty("line.separator"));
        p = 0;
        q = 0;
        lc = 0;

        while (sc.hasNext()) {
            ins = sc.nextLine().trim().split(" ");
            if (!chk_pseudo(ins))
                chk_mnemon(ins);
        }

        fw.write(System.getProperty("line.separator"));
        fw.write("Pass 2" + System.getProperty("line.separator"));
        fw.write("Address\tInstruction" + System.getProperty("line.separator"));
        sc = new Scanner(new File("Input.txt"));
        lc = 0;

        while (sc.hasNext()) {
            ins = sc.nextLine().trim().split(" ");
            if (!chk_pseudo2(ins))
                chk_mnemon2(ins);
        }

        System.out.println("\nSymbol Table");
        for (int i = 0; i < 5; i++) {
            if (ST[i][0] == null)
                break;
            for (int j = 0; j < 4; j++)
                System.out.print(ST[i][j] + " ");
            System.out.println();
        }

        System.out.println("\nLiteral Table");
        for (int i = 0; i < 5; i++) {
            if (LT[i][0] == null)
                break;
            for (int j = 0; j < 4; j++)
                System.out.print(LT[i][j] + " ");
            System.out.println();
        }

        System.out.println("\nBase Table");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(BT[i][j] + " ");
            }
            System.out.println();
        }

        fw.close();
    }

    public static Boolean chk_pseudo(String arr[]) throws Exception {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(POT[0][0])) {
                fw.write(System.getProperty("line.separator"));
                ST[p][0] = arr[i - 1];
                ST[p][1] = Integer.toString(lc);
                ST[p][2] = "1";
                ST[p][3] = "RX";
                p++;
                return true;
            }

            if (arr[i].equals(POT[2][0])) {
                fw.write(lc + "\t " + arr[i + 1].charAt(1));
                fw.write(System.getProperty("line.separator"));
                ST[p][0] = arr[i - 1];
                ST[p][1] = Integer.toString(lc);
                ST[p][2] = "4";
                ST[p][3] = "RX";
                p++;
                lc = lc + 4;
                return true;
            }

            if (arr[i].equals(POT[3][0])) {
                fw.write(Integer.toString(lc));
                fw.write(System.getProperty("line.separator"));
                ST[p][0] = arr[i - 1];
                ST[p][1] = Integer.toString(lc);
                ST[p][2] = "4";
                ST[p][3] = "RX";
                p++;
                lc = lc + 4;
                return true;
            }

            if (arr[i].equals(POT[4][0])) {
                LT[q][1] = Integer.toString(lc);
                return true;
            }
        }
        return false;
    }

    public static void chk_mnemon(String arr[]) throws Exception {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < MOT.length; j++) {

                if (arr[i].equals(MOT[j][0])) {
                    fw.write(lc + "\t " + arr[i] + "\t(" + arr[i + 1] + ", -(0, 15))");
                    fw.write(System.getProperty("line.separator"));
                    if ((arr[2].charAt(0)) == '=') {
                        LT[q][0] = arr[2];
                        LT[q][2] = "4";
                        LT[q][3] = "RX";
                    }
                    lc = lc + 4;
                }
            }
        }
    }

    public static Boolean chk_pseudo2(String arr[]) throws Exception {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(POT[1][0])) {
                BT[(Integer.parseInt(arr[2])) - 1][1] = "Y";
                BT[(Integer.parseInt(arr[2])) - 1][2] = arr[1];
                return true;
            }
            if (arr[i].equals(POT[2][0])) {
                fw.write(lc + "\t " + arr[i + 1].charAt(1));
                fw.write(System.getProperty("line.separator"));
                lc = lc + 4;
                return true;
            }
            if (arr[i].equals(POT[3][0])) {
                fw.write(Integer.toString(lc));
                fw.write(System.getProperty("line.separator"));
                lc = lc + 4;
                return true;
            }
        }
        return false;
    }

    public static void chk_mnemon2(String arr[]) throws Exception {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < MOT.length; j++) {
                if (arr[i].equals(MOT[j][0])) {
                    for (int l = 0; l < ST.length; l++) {
                        if (arr[2].equals(ST[l][0])) {
                            fw.write(lc + "\t " + arr[i] + "\t(" + arr[i + 1] + ", " + ST[l][1] + "(0, 15))");
                        }
                    }
                    for (int l = 0; l < LT.length; l++) {
                        if (arr[2].equals(LT[l][0])) {
                            fw.write(lc + "\t " + arr[i] + "\t(" + arr[i + 1] + ", " + LT[l][1] + "(0, 15))");
                        }

                    }
                    fw.write(System.getProperty("line.separator"));
                    lc = lc + 4;
                }
            }
        }
    }
}
