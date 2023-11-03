import java.util.*;
import java.io.*;

public class Main {

    static long[] dp = new long[31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int maxN = Math.max(n, m - n);
            int minN = Math.min(n, m - n);
            long up = mul(maxN, m);
            long down = factorial(minN);
            bw.write((up / down) + "\n");
        }
        bw.flush(); bw.close();
    }

    public static long mul(int a, int b) {
        long total = 1;
        for (int i = a + 1; i <= b; i++) {
            total *= i;
        }
        return total;
    }

    public static long factorial(int n) {
        dp[0] = 1; dp[1] = 1;
        if (dp[n] != 0) return dp[n];

        for (int i = 2; i <= n; i++) {
            if (dp[i] == 0) {
                dp[i] = dp[i - 1] * i;
            }
        }
        return dp[n];
    }
}