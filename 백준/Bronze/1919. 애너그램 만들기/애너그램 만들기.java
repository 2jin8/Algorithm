import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int[] alphabetA = new int[26];
        int[] alphabetB = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            alphabetA[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            alphabetB[s2.charAt(i) - 'a']++;
        }

        int total = 0;
        for (int i = 0; i < alphabetA.length; i++) {
            if (alphabetA[i] != alphabetB[i]) {
                total += Math.abs(alphabetA[i] - alphabetB[i]);
            }
        }
        System.out.println(total);
    }
}