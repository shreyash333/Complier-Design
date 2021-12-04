import java.util.*;
import java.io.*;

public class MACRO {

    public static void main(String[] args) throws Exception {
        String MNT[] = new String[10];
        String MDT[] = new String[10];
        Scanner sc = new Scanner(new File("Input.txt"));
        FileWriter fw = new FileWriter(new File("MacroPass1Output.txt"));
        int mdtc = 1;
        int mntc = 1;
        String temp;

        while (sc.hasNext()) {
            temp = sc.nextLine();
            if (temp.contains("MACRO")) {
                temp = sc.nextLine().trim();
                MNT[mntc] = mntc + "\t" + temp + "\t" + mdtc;
                mntc++;
                MDT[mdtc] = mdtc + "\t" + temp;
                mdtc++;
                do {
                    temp = sc.nextLine().trim();
                    
                    MDT[mdtc] = mdtc + "\t" + temp;
                    mdtc++;

                } while (!(temp.equals("MEND")));
                

            } else
                fw.write("\n"+temp);
        }

        System.out.println("\nMNT:");
        for (int i = 1; i < mntc; i++) {
            System.out.println(MNT[i]);
        }

        System.out.println("\nMDT:");
        for (int i = 1; i < mdtc; i++) {
            System.out.println(MDT[i]);
        }
        fw.close();
    }
}
