import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + dp[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}