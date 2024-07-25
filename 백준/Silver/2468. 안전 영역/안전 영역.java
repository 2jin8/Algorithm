import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int MAX = 0, MIN = 101;
    private static int[][] region;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        region = new int[N][N];

        int minH = MIN, maxH = MAX;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, region[i][j]);
                maxH = Math.max(maxH, region[i][j]);
            }
        }

        // 높이는 minH - 1 ~ maxH까지 계산
        int result = 0;
        for (int i = minH - 1; i <= maxH; i++) {
            int[][] tmp = new int[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (region[j][k] <= i) {
                        tmp[j][k] = -1;
                    } else {
                        tmp[j][k] = region[j][k];
                    }
                }
            }

            int cnt = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (tmp[j][k] != -1) {
                        bfs(tmp, j, k);
                        cnt++;
                    }
                }
            }
            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }

    private static void bfs(int[][] ary, int i, int j) {
        ary[i][j] = -1;

        // 양방향 체크
        for (int k = 0; k < 4; k++) {
            int tx = i + dx[k];
            int ty = j + dy[k];

            if (tx < 0 || tx >= N || ty < 0 || ty >= N)
                continue;

            if (ary[tx][ty] != -1) {
               bfs(ary, tx, ty);
            }
        }
    }
}