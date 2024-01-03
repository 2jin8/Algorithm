import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean mix = isMix(st.nextToken(), st.nextToken());
            sb.append(mix ? "Possible" : "Impossible").append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isMix(String s1, String s2) {
        int[] alphabetA = new int[26];
        int[] alphabetB = new int[26];
        if (s1.length() != s2.length()) return false;
        
        for (int i = 0; i < s1.length(); i++) {
            alphabetA[s1.charAt(i) - 'a']++;
            alphabetB[s2.charAt(i) - 'a']++;
        }
        return Arrays.equals(alphabetA, alphabetB);
    }
}