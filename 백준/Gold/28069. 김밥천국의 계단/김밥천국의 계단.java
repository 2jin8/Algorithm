import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int next = i + 1;
            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }

            next = i + i / 2;
            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }
        }
        System.out.println(dp[N] <= K ? "minigimbob" : "water");
    }
}