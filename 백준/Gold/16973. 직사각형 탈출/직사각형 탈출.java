import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, W, n, m;
    static int[][] map, arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        int er = Integer.parseInt(st.nextToken()) - 1;
        int ec = Integer.parseInt(st.nextToken()) - 1;

        n = N - H + 1;
        m = M - W + 1;
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < H; k++) {
                    for (int l = 0; l < W; l++) {
                        arr[i][j] += map[i + k][j + l];
                    }
                }
            }
        }

        visited = new boolean[n][m];
        bfs(sr, sc, er, ec);
    }

    public static void bfs(int sr, int sc, int er, int ec) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        int[][] times = new int[n][m];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int time = -1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x == er && y == ec) {
                time = times[x][y];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (arr[nx][ny] == 0 && !visited[nx][ny]) {
                    times[nx][ny] = times[x][y] + 1;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        System.out.println(time);
    }
}