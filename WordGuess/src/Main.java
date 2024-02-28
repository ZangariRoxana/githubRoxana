
import java.util.Scanner;
public class Main {


    public static char readFromConsoleChar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIntroduceti litera: ");
        return scanner.nextLine().charAt(0);
    }

    public static String readFromConsoleString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti cuvant: ");
        return scanner.nextLine();
    }
    public static int countOccurence(char c, char[] word) {

        int count = 0;
        for (char ch : word) {
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String word = readFromConsoleString();
        int nrghicite=0;
        int incercari=0;
        int[]fr=new int[125];
        while(nrghicite!=word.length()) {
            char c = readFromConsoleChar();
            int count = countOccurence(c, word.toCharArray());
            if(count!=0 && fr[c]==0) {
                nrghicite=nrghicite+count;
                fr[c]=count;
                System.out.println("Litera " + c + " apare de " + count + " ori in cuvant ");
                for(int i=0;i<word.length();i++) {
                    if(fr[word.charAt(i)]!=0)
                        System.out.print(word.charAt(i)+"");
                    else System.out.print("_");
                }
            }

            else if(fr[c]!=0 && count!=0)
                System.out.println("Litera deja introdusa");
            else
                System.out.println("Din pacate litera "+c+" nu apare in cuvant");
            incercari++;
        }
        System.out.println("\nFelicitari ! \nAi gasit cuvantul '"+word+"' in "+incercari+" incercari");



    }

}
