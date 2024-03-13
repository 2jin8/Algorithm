import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][10];
        // 한 자리수
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // 두 자리수 ~ N 자리수
        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1] % MOD;

            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] % MOD) + (dp[i - 1][j + 1] % MOD);
            }

            dp[i][9] = dp[i - 1][8] % MOD;
        }

        long total = 0;
        for (int i = 1; i <= 9; i++) {
            total = (total + dp[n][i]) % MOD;
        }
        System.out.println(total);
    }
}