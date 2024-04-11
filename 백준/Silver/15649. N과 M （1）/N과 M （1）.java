import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        used = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            used[i] = true;
            dfs(i + " ", 1);
            used[i] = false;
        }
        System.out.println(sb.toString());
    }

    public static void dfs(String num, int depth) {
        if (depth == M) {
            sb.append(num).append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(num + i + " ", depth + 1);
                used[i] = false;
            }
        }
    }
}