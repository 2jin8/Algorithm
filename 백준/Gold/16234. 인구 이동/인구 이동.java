import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] land, tmp;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        land = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            tmp = new int[N][N]; // 인구 이동을 위한 임시 배열
            for (int i = 0; i < N; i++) {
                tmp[i] = land[i].clone();
            }
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(new Point(i, j));
                    }
                }
            }
            if (equals()) { // 인구 이동 전이랑 후가 동일한 경우
                break;
            }
            day++;
            for (int i = 0; i < N; i++) {
                land[i] = tmp[i].clone();
            }
        }
        System.out.println(day);
    }

    public static boolean equals() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (land[i][j] != tmp[i][j]) return false;
            }
        }
        return true;
    }

    public static void bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        visited[point.x][point.y] = true;

        int total = 0, cnt = 0;
        List<Point> list = new ArrayList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            point = queue.poll();
            list.add(point);
            int x = point.x;
            int y = point.y;
            total += land[x][y];
            cnt++;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= N) continue;

                int diff = Math.abs(land[x][y] - land[tx][ty]);
                if (!visited[tx][ty] && diff >= L && diff <= R) {
                    visited[tx][ty] = true;
                    queue.offer(new Point(tx, ty));
                }
            }
        }
        if (total == 0) return;
        int newTotal = total / cnt;
        for (Point p : list) { // 인구 조절
            tmp[p.x][p.y] = newTotal;
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