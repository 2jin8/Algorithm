import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 30;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[MAX + 1][MAX + 1];
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) dp[i][j] = i;
                else if (i == j) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            sb.append(dp[n][k]).append("\n");
        }
        System.out.println(sb);
    }
}