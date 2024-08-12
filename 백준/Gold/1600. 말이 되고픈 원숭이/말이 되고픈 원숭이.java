import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[K + 1][H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        int[] monkeyX = {1, -1, 0, 0};
        int[] monkeyY = {0, 0, 1, -1};
        int[] horseX = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] horseY = {1, -1, 1, -1, 2, -2, 2, -2};
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.x == H - 1 && now.y == W - 1) {
                return now.dist;
            }

            // 말처럼 이동할 기회가 남은 경우
            if (now.cnt < K) {
                // 말처럼 이동하기
                for (int i = 0; i < 8; i++) {
                    int nx = now.x + horseX[i];
                    int ny = now.y + horseY[i];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                        continue;
                    }

                    if (!visited[now.cnt + 1][nx][ny] && map[nx][ny] == 0) {
                        queue.offer(new Point(nx, ny, now.cnt + 1, now.dist + 1));
                        visited[now.cnt + 1][nx][ny] = true;
                    }
                }
            }

            // 원숭이처럼 이동하기
            for (int i = 0; i < 4; i++) {
                int nx = now.x + monkeyX[i];
                int ny = now.y + monkeyY[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                    continue;
                }

                if (!visited[now.cnt][nx][ny] && map[nx][ny] == 0) {
                    queue.offer(new Point(nx, ny, now.cnt, now.dist + 1));
                    visited[now.cnt][nx][ny] = true;
                }
            }
        }
        return -1;
    }

    static class Point {
        int x, y;
        int cnt; // 말처럼 이동한 횟수
        int dist; // 총 이동 횟수

        public Point(int x, int y, int cnt, int dist) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dist = dist;
        }
    }
}