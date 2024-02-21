import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        // 1개 또는 3개씩 가져갈 수 있음
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 3 == 0) dp[i] = i / 3;
            else dp[i] = dp[i - 1] + 1;
        }
        System.out.println(dp[n] % 2 == 0 ? "CY" : "SK");
    }
}