import java.io.*;

public class Main {
    static int MAX = 11;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // DP 값 계산하기
        int[] dp = new int[MAX + 1];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for (int i = 4; i <= MAX; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // 입력한 수에 맞는 DP 값 출력하기
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }
}