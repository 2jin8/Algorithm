import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] S;
    static boolean[] used;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            sum += S[i];
        }

        used = new boolean[sum + 2];
        dfs(0, 0);

        for (int i = 0; i <= sum + 1; i++) {
            if (!used[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    // 부분 수열
    // 선택 O, 선택 X
    static void dfs(int depth, int total) {
        if (depth == N) {
            used[total] = true;
            return;
        }

        dfs(depth + 1, total + S[depth]); // 선택 O
        dfs(depth + 1, total); // 선택 X
    }
}
