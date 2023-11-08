import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dp = new int[1001];
    static int[] p = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = p[1];
        dp[2] = Math.max(p[2], dp[1] + p[1]);
        for (int i = 3; i <= n; i++) {
            dp[i] = p[i];
            for (int j = i - 1; j >= 1; j--) {
                dp[i] = Math.max(dp[i], dp[j] + p[i - j]);
            }
        }
        System.out.println(dp[n]);
    }
}