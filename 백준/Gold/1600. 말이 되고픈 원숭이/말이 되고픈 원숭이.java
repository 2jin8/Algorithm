import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] board;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][W];
        visited = new boolean[H][W][K + 1]; // K번 점프 가능 (상태 변경!)
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[] hx = {2, -2, 2, -2, 1, -1, 1, -1};
        int[] hy = {1, 1, -1, -1, 2, 2, -2, -2};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            if (x == H - 1 && y == W - 1) {
                return point.move;
            }

            // 인접한 네 방향으로 움직이기
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= H || ty >= W) continue;

                if (!visited[tx][ty][point.cnt] && board[tx][ty] == 0) {
                    visited[tx][ty][point.cnt] = true;
                    queue.offer(new Point(tx, ty, point.cnt, point.move + 1));
                }
            }

            // 말의 움직임으로 움직이기
            if (point.cnt < K) { // 이동 횟수가 남았다면
                for (int i = 0; i < 8; i++) {
                    int tx = x + hx[i];
                    int ty = y + hy[i];
                    if (tx < 0 || ty < 0 || tx >= H || ty >= W) continue;

                    if (!visited[tx][ty][point.cnt + 1] && board[tx][ty] == 0) {
                        visited[tx][ty][point.cnt + 1] = true;
                        queue.offer(new Point(tx, ty, point.cnt + 1, point.move + 1));
                    }
                }
            }
        }
        return -1;
    }
}

class Point {
    int x;
    int y;
    int cnt; // 말처럼 이동한 횟수
    int move; // 동작 횟수

    public Point(int x, int y, int cnt, int move) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.move = move;
    }
}
