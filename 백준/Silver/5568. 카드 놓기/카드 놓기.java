import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] cards;
    static boolean[] used;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        // k개 선택
        cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            used = new boolean[n];
            used[i] = true;
            dfs(1, cards[i]);
        }
        System.out.println(set.size());
    }

    public static void dfs(int depth, int total) {
        if (depth == k) {
            set.add(total);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                int len = String.valueOf(cards[i]).length();
                dfs(depth + 1, total * (int) Math.pow(10, len) + cards[i]);
                used[i] = false;
            }
        }
    }
}