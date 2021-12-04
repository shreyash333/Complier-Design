import java.util.*;

public class LeftRecursion {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
         System.out.print("Enter a Left Recursion Production in A -> A+B / B format : ");
         String str = sc.nextLine();
        ArrayList<String> output = eliminateLeftRecursion(str);
        
        sc.close();

        System.out.println("Eliminated Production : ");
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }

    }

    public static ArrayList<String> eliminateLeftRecursion(String input) {
        char[] ch = new char[input.length()];
        char[] a = new char[input.length()];
     
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                ch[count] = input.charAt(i);
                count++;
            }
        }

        a[0] = ch[0];
       
        String temstring = new String(ch);
        String substring = temstring.substring(3);
        //System.out.println("substring");
        //System.out.println(substring);
        String[] rhsProduction = substring.split("/");
        //System.out.println("Prodcution Separated");
        ArrayList<String> answer = new ArrayList<String>();
        // String[] answer = new String[rhsProduction.length + 1];
        //System.out.println(Arrays.toString(rhsProduction));
        for (int u = 0; u < rhsProduction.length; u++) {

            String cuurentProduction = rhsProduction[u];
            // trying to remove blaank spaces
            //char[] tempspacelessstring = new char[cuurentProduction.length()];
            //int temcount = 0;
            //for (int i = 0; i < cuurentProduction.length(); i++) {
            //    if (cuurentProduction.charAt(i) != ' ') {
            //        tempspacelessstring[temcount] = cuurentProduction.charAt(i);
            //        temcount++;
            //    }
            //}
            //String newcuurentProduction = new String(tempspacelessstring);
            //
            //cuurentProduction = cuurentProduction.replaceAll("\\s", "");
            //cuurentProduction = cuurentProduction.strip();
            //System.out.println(newcuurentProduction + "s");
            char firstchar = cuurentProduction.charAt(0);
            // char firstchar = getCharFromString(cuurentProduction, 0);
            // System.out.println("fristChar");
            //System.out.println(firstchar);
            if (firstchar == a[0]) {
                String fristCharRemove = cuurentProduction.substring(1);
                //System.out.println("fristCharRemove");
                //System.out.println(fristCharRemove);
                String convertedProduction = firstchar + "'" + " -> " + fristCharRemove + firstchar + "'";
                answer.add(convertedProduction);
            } else {
                String convertedProduction = a[0] + " -> " + cuurentProduction + a[0] + "'";
                answer.add(convertedProduction);
            }

        }
        //System.out.println("answer");
        answer.add(a[0] + "'" + " -> " + "Epsilon");
        //for (int i = 0; i < answer.size(); i++) {
        //    System.out.println(answer.get(i));
        //} // length is the property of the array

        

        return answer;

    }

    

}

// OUTPUT

//C:\Users\Asus\OneDrive\Desktop\Java Project>javac LeftRecursion.java
//
//C:\Users\Asus\OneDrive\Desktop\Java Project>java LeftRecursion
//Enter a Left Recursion Production in A -> A+B / B format : S -> Sa / Sb / ab / ba
//Eliminated Production :
//S' -> aS'
//S' -> bS'
//S -> abS'
//S -> ba        S'
//S' -> Epsilon
