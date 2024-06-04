import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            HashMap<Character, Integer> countMap = new HashMap<>();
            String word = br.readLine();
            sb.setLength(0);
            int idx = 1;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (countMap.containsKey(c)) {
                    sb.append(countMap.get(c));
                } else {
                    sb.append(idx);
                    countMap.put(c, idx++);
                }
            }
            words[i] = sb.toString();
        }

        int pair = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].equals(words[j])) {
                    pair++;
                }
            }
        }
        System.out.println(pair);
    }
}