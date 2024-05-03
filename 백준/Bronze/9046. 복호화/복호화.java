import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int[] alphabet = new int[26];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                for (int j = 0; j < str.length(); j++) {
                    alphabet[str.charAt(j) - 'a']++;
                }
            }
            int max = Arrays.stream(alphabet).max().getAsInt();
            int count = 0; //
            char answer = ' ';
            for (int j = 0; j < 26; j++) {
                if (max == alphabet[j]) {
                    if (count == 1) {
                        answer = '?';
                        break;
                    } else {
                        count++;
                        answer = (char) ('a' + j);
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
}