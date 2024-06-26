import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] prev = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) dp[1] = 0;
            else if (i == 2 || i == 3) {
                dp[i] = 1;
                prev[i] = 1;
            } else {
                dp[i] = dp[i - 1] + 1;
                prev[i] = i - 1;

                if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                    dp[i] = dp[i / 3] + 1;
                    prev[i] = i / 3;
                }

                if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                    dp[i] = dp[i / 2] + 1;
                    prev[i] = i / 2;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]).append("\n");
        sb.append(n).append(" ");
        int p = n;
        while (true) {
            if (prev[p] == 0) break;

            sb.append(prev[p]).append(" ");
            p = prev[p];
        }
        System.out.println(sb.toString());
    }
}