import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i - 1] + arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            // i번째 수부터 j번째 수까지의 합 = 1부터 j번째 수까지의 합 - 1부터 i-1번째 수까지의 합
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dp[b] - dp[a - 1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}