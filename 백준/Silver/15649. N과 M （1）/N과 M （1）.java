import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[] used;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        used = new boolean[N + 1];
        answer = new int[M];
        for (int i = 1; i <= N; i++) {
            used[i] = true;
            answer[0] = i;
            dfs(1);
            used[i] = false;
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int depth) {
        if (depth == M) {
            for (int a : answer) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                used[i] = true;
                answer[depth] = i;
                dfs(depth + 1);
                used[i] = false;
            }
        }
    }
}