import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < i + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (i == j) {
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }

        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, dp[n - 1][i]);
        }
        System.out.println(maxSum);
    }
}