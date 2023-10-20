import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = str.charAt(j - 1) - '0';
            }
        }

        bfs(1, 1);
        System.out.println(board[n][m]);
    }

    public static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            x = point.x;
            y = point.y;
            if (x == n && y == m)
                return;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (tx <= 0 || ty <= 0 || tx > n || ty > m) // 범위를 벗어나는 경우
                    continue;

                if (board[tx][ty] == 0) // 이동할 수 없는 칸인 경우
                    continue;

                if (board[tx][ty] == 1) { // 이동할 수 있는 칸 & 아직 방문하지 않은 경우
                    board[tx][ty] = board[x][y] + 1;
                    queue.add(new Point(tx, ty));
                }
            }
        }
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