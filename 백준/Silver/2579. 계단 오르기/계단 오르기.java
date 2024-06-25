import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            if (i == 1) dp[i] = arr[1];
            else if (i == 2) dp[i] = arr[1] + arr[2];
            else if (i == 3) dp[i] = Math.max(arr[1], arr[2]) + arr[3];
            else dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
        }
        System.out.println(dp[n]);
    }
}