import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] cost, dp[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[N][M];
        dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) Arrays.fill(dp[i][j], cost[i][j]);
                else Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 이전 블럭에서 0 방향에서 왔다면, 그 블럭은 1 or 2 방향으로만 왔어야 함 (j가 0일 때는 제외)
                if (j != 0)
                    dp[i][j][0] = cost[i][j] + Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]);

                // 이전 블럭에서 1 방향에서 왔다면, 그 블럭은 0 or 2 방향으로만 왔어야 함
                dp[i][j][1] = cost[i][j] + Math.min(dp[i - 1][j][0], dp[i - 1][j][2]);

                // 이전 블럭에서 2 방향에서 왔다면, 그 블럭은 0 or 1 방향으로만 왔어야 함 (j가 M-1일 때는 제외)
                if (j != M - 1)
                    dp[i][j][2] = cost[i][j] + Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                answer = Math.min(answer, dp[N - 1][j][k]);
            }
        }
        System.out.println(answer);
    }
}
