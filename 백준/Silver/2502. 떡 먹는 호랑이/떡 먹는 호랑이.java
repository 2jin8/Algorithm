import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int D, K;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[2][D + 1];
        dp[0][1] = 1;
        dp[1][2] = 1;
        for (int i = 3; i <= D; i++) {
            dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
            dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
        }

        for (int a = 1; dp[0][D] * a < K; a++) {
            for (int b = a; dp[1][D] * b < K; b++) {
                if (dp[0][D] * a + dp[1][D] * b == K) {
                    System.out.println(a);
                    System.out.println(b);
                    return;
                }
            }
        }
    }
}
