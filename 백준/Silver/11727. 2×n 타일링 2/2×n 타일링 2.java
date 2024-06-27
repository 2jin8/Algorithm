import java.io.*;

public class Main {
    static final int DIV = 10_007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) dp[i] = 1;
            else if (i == 2) dp[i] = 3;
            else dp[i] = (dp[i - 1] + dp[i - 2] * 2) % DIV; // 2×2를 만드는 방법이 총 2개이므로
        }
        System.out.println(dp[n]);
    }
}