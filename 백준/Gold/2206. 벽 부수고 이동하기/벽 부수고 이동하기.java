import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        board = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, false, 0));
        visited[0][0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == N - 1 && point.y == M - 1) {
                return point.move + 1;
            }

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                if (board[tx][ty] == 1) { // 벽인 경우
                    if (!point.crashed) { // 벽을 부수지 않은 경우
                        queue.offer(new Point(tx, ty, true, point.move + 1));
                        visited[tx][ty][1] = true;
                    }
                } else {
                    if (point.crashed) { // 이미 벽을 부순 경우
                        if (!visited[tx][ty][1]) {
                            queue.offer(new Point(tx, ty, true, point.move + 1));
                            visited[tx][ty][1] = true;
                        }
                    } else { // 벽을 부수지 않은 경우
                        if (!visited[tx][ty][0]) {
                            queue.offer(new Point(tx, ty, false, point.move + 1));
                            visited[tx][ty][0] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    static class Point {
        int x;
        int y;
        boolean crashed; // 벽 부쉈는지 여부
        int move; // 이동 횟수

        public Point(int x, int y, boolean crashed, int move) {
            this.x = x;
            this.y = y;
            this.crashed = crashed;
            this.move = move;
        }
    }
}