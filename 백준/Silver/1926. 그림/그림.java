import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0, max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    max = Math.max(max, bfs(board, visited, i, j));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }

    static int bfs(int[][] board, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dy = {0, 0, -1, 1};
        int area = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            area++;
            x = point.x;
            y = point.y;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                    continue;

                if (!visited[tx][ty] && board[tx][ty] == 1) {
                    visited[tx][ty] = true;
                    board[tx][ty] = board[x][y] + 1;
                    queue.offer(new Point(tx, ty));
                }
            }
        }
        return area;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}