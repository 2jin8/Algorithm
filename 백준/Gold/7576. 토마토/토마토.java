import java.io.*;
import java.util.*;

public class Main {
    static int m, n;
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        int[][] box = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        if (isZero(box)) { // 모두 익어있는 경우
            System.out.println(0);
            return;
        }

        int days = bfs(box);
        if (isZero(box)) System.out.println(days);
        else System.out.println(-1);
    }

    private static int bfs(int[][] box) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        int x = 0, y = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            x = point.x;
            y = point.y;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                    continue;

                if (box[tx][ty] == 0) {
                    queue.offer(new Point(tx, ty));
                    box[tx][ty] = box[x][y] + 1;
                }
            }
        }
        return box[x][y] - 1;
    }

    private static boolean isZero(int[][] box) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0)
                    return false;
            }
        }
        return true;
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
