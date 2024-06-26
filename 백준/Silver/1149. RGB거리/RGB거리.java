import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j];
            }
        }

        for (int i = 1; i < n; i++) {
            // 현재 집에 빨강을 칠하는 경우, 이전 집은 초록이나 파랑만 가능
            dp[i][0] += Math.min(dp[i - 1][1], dp[i - 1][2]);
            // 현재 집에 초록을 칠하는 경우, 이전 집은 빨강이나 파랑만 가능
            dp[i][1] += Math.min(dp[i - 1][0], dp[i - 1][2]);
            // 현재 집에 파랑을 칠하는 경우, 이전 집은 빨강이나 초록만 가능
            dp[i][2] += Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        System.out.println(Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2])));
    }
}