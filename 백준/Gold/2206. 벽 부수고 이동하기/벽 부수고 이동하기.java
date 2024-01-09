import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 0: 벽을 부수지 않은 경우, 1: 벽을 부순 경우
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        int[][] dist = new int[N][M];
        q.offer(new Point(0, 0, false));
        visited[0][0][0] = true;
        dist[0][0] = 1;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            Point point = q.poll();
            if (point.x == N - 1 && point.y == M - 1) {
                return dist[N - 1][M - 1];
            }

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M)
                    continue;

                if (map[tx][ty] == 0) { // 벽이 아닌 경우
                    if (point.crashed && !visited[tx][ty][1]) { // 벽을 부순적이 있는 경우
                        q.offer(new Point(tx, ty, true));
                        visited[tx][ty][1] = true;
                        dist[tx][ty] = dist[point.x][point.y] + 1;
                    } else if (!point.crashed && !visited[tx][ty][0]) {
                        q.offer(new Point(tx, ty, false));
                        visited[tx][ty][0] = true;
                        dist[tx][ty] = dist[point.x][point.y] + 1;
                    }
                } else { // 벽인 경우
                    if (!point.crashed) { // 벽을 부순적이 없는 경우
                        q.offer(new Point(tx, ty, true));
                        visited[tx][ty][1] = true;
                        dist[tx][ty] = dist[point.x][point.y] + 1;
                    }
                }
            }
        }
        return -1;
    }

    static class Point {
        int x;
        int y;
        boolean crashed; // 벽을 부쉈는지 여부

        public Point(int x, int y, boolean crashed) {
            this.x = x;
            this.y = y;
            this.crashed = crashed;
        }
    }
}