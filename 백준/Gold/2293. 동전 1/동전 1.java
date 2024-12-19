import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        coins = new int[n + 1];
        dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) { // coins 반복
            int coin = coins[i]; // 1, 2, 5원
            for (int j = coin; j <= k; j++) { // 1~10, 2~10, 5~10
                if (coin == j) {
                    dp[j] = dp[j] + 1;
                } else {
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
        }
        System.out.println(dp[k]);
    }
}