import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        long[][] dp = new long[N][N]; // (0, 0)에서 각 점까지 갈 수 있는 횟수 기록
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) { // 각 점에서 이동하기
            for (int j = 0; j < N; j++) {
                int move = map[i][j];
                if (move == 0) continue;
                // 오른쪽으로 가기
                if (j + move < N) dp[i][j + move] += dp[i][j];
                // 아래쪽으로 가기
                if (i + move < N) dp[i + move][j] += dp[i][j];
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}