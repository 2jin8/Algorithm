import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 0, true));
        visited[0][0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            if (x == N - 1 && y == M - 1) {
                return point.move + 1;
            }

            // 밤이라면 같은 칸에 머물기(다음 날에 벽을 부술수도 있으니)
            if (!point.day) {
                queue.offer(new Point(x, y, point.move + 1, point.crashed, !point.day));
                visited[x][y][point.crashed] = true;
            }

            // 상하좌우로 이동
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M)
                    continue;

                if (map[tx][ty] == 1 && point.day) { // 다음 칸이 벽이고 낮인 경우
                    if (point.crashed < K && !visited[tx][ty][point.crashed + 1]) { // 아직 벽을 부술 기회가 남은 경우
                        queue.offer(new Point(tx, ty, point.move + 1, point.crashed + 1, !point.day));
                        visited[tx][ty][point.crashed + 1] = true;
                    }
                } else if (map[tx][ty] == 0) { // 다음 칸이 벽이 아닌 경우
                    if (!visited[tx][ty][point.crashed]) {
                        queue.offer(new Point(tx, ty, point.move + 1, point.crashed, !point.day));
                        visited[tx][ty][point.crashed] = true;
                    }
                }
            }
        }

        return -1;
    }

    static class Point {
        int x;
        int y;
        int move; // 이동 횟수
        int crashed; // 벽을 부순 횟수
        boolean day; // true: 낮, false: 밤

        public Point(int x, int y, int move, int crashed, boolean day) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.crashed = crashed;
            this.day = day;
        }
    }
}