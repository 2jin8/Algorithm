import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] cards = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cards[i] = Integer.parseInt(str[i - 1]);
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = cards[i];
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }
        System.out.println(dp[n]);
    }
}