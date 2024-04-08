import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 1_000_001;
    static int N, K;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int next = i + 1; // 한 칸 올라가기
            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }

            next = i + i / 2; // 순간이동하기
            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }
        }
        System.out.println(dp[N] <= K ? "minigimbob" : "water");
    }
}