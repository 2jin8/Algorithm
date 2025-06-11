import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static boolean[] used = new boolean[26];
    static char[] keys = new char[26];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 0;
        for (char c : br.readLine().toCharArray()) {
            if (used[c - 'A']) continue;

            keys[idx++] = c;
            used[c - 'A'] = true;
        }

        int last = 0;
        for (int i = idx; i < 26; i++) {
            for (int j = last; j < 26; j++) {
                if (used[j]) continue;

                keys[i] = (char) ('A' + j);
                used[j] = true;
                last = j + 1;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : br.readLine().toCharArray()) {
            sb.append(keys[c - 'A']);
        }
        System.out.println(sb);
    }
}
