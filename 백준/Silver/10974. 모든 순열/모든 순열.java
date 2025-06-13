import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static boolean[] used;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        used = new boolean[N + 1];
        answer = new int[N];

        dfs(0);
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (depth == N) {
            for (int a : answer) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i]) continue;

            used[i] = true;
            answer[depth] = i;
            dfs(depth + 1);
            used[i] = false;
        }
    }
}
