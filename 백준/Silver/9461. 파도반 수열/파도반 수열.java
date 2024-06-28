import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        int max = 0;
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long[] dp = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            if (i <= 3) dp[i] = 1;
            else if (i <= 5) dp[i] = 2;
            else dp[i] = dp[i - 1] + dp[i - 5];
        }

        StringBuilder sb = new StringBuilder();
        for (int a: arr) {
            sb.append(dp[a]).append("\n");
        }
        System.out.println(sb.toString());
    }
}