import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, ans;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];
        visited = new boolean[N][M];
        Point start = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) start = new Point(i, j);
            }
        }
        bfs(start);
        check();  // 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치 체크하기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && ans[i][j] == 0) ans[i][j] = -1;
            }
        }
    }

    public static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                if (!visited[tx][ty] && map[tx][ty] == 1) {
                    queue.offer(new Point(tx, ty));
                    visited[tx][ty] = true;
                    ans[tx][ty] = ans[point.x][point.y] + 1;
                }
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}