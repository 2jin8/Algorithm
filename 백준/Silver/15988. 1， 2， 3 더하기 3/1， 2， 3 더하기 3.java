import java.io.*;

public class Main {
    static final int MAX = 1_000_000;
    static final int DIV = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long[] dp = new long[MAX + 1];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for (int i = 4; i <= max; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % DIV;
        }

        StringBuilder sb = new StringBuilder();
        for (int a : arr) {
            sb.append(dp[a]).append("\n");
        }
        System.out.println(sb);
    }
}