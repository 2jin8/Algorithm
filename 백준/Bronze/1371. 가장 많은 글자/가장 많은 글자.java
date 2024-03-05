import java.io.*;
import java.util.*;

public class Main {
    static int[] alphabet = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        str = sb.toString();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                alphabet[str.charAt(i) - 'a']++;
            }
        }

        int max = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(alphabet[i], max);
        }
        sb = new StringBuilder();
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == max) sb.append((char) (i + 'a'));
        }
        System.out.println(sb);
    }
}