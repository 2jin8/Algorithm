import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] castle;
    static boolean[][][] visited; // 0: 그람 획득 X, 1: 그람 획득 O
    public static void main(String[] args) throws Exception {
        // 그람 획득 → 벽 상관없이 이동 가능 → 그람 획득 O / X → 총 2가지 상태
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        castle = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = bfs();
        System.out.println(ans == -1 || ans > T ? "Fail" : ans);
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, false));
        visited[0][0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == N - 1 && point.y == M - 1) {
                return point.time;
            }

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                if (point.gram) { // 그람을 획득한 경우
                    if (!visited[tx][ty][1]) { // 벽 상관없이 이동 가능
                        visited[tx][ty][1] = true;
                        queue.offer(new Point(tx, ty, point.time + 1, true));
                    }
                } else { // 그람을 획득하지 못한 경우
                    if (castle[tx][ty] == 0 && !visited[tx][ty][0]) {
                        visited[tx][ty][0] = true;
                        queue.offer(new Point(tx, ty, point.time + 1, false));
                    } else if (castle[tx][ty] == 2 && !visited[tx][ty][0]) {
                        queue.offer(new Point(tx, ty, point.time + 1, true));
                    }
                }
            }
        }
        return -1;
    }

    static class Point {
        int x;
        int y;
        int time;
        boolean gram; // 그람 획득 여부

        public Point(int x, int y, int time, boolean gram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.gram = gram;
        }
    }
}