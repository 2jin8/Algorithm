import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[][] map, dp;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1); // 방문 확인을를 위해 -1로 초기화
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    static int dfs(int x, int y) {
        // 마지막 위치에 도달하면 경로가 존재하는 것 == 1 반환
        if (x == M - 1 && y == N - 1)
            return 1;

        // 해당 칸을 아직 방문하지 않은 경우
        if (dp[x][y] == -1) {
            dp[x][y] = 0; // 방문 처리

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;

                // 더 낮은 곳으로 이동하는 경우
                if (map[nx][ny] < map[x][y]) {
                    // 네 방향 다 살펴보면서 해당 위치에서 도달 가능한 경로의 수 구하기
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }
        // 이미 방문했다면 해당 위치에서 도달 가능한 경로의 수 반환
        return dp[x][y];
    }
}
