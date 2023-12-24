import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        // 벽 최소 몇 개 부수어야 하느냐 - 1일 때마다 수 카운팅 -> BFS 탐색
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(str[j]);
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        // 벽을 부순 개수 기준으로 오름차순 정렬 ⇒ 벽을 부순 개수가 적은 것부터 poll
        PriorityQueue<Point> pq = new PriorityQueue<>(); 
        pq.offer(new Point(0, 0, 0));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!pq.isEmpty()) {
            Point point = pq.poll();
            if (point.x == n - 1 && point.y == m - 1) {
                return point.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                    continue;

                if (!visited[tx][ty]) {
                    pq.offer(new Point(tx, ty, point.cnt + board[tx][ty]));
                    visited[tx][ty] = true;
                }
            }
        }
        return -1;
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;
    int cnt; // 벽을 부순 개수

    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Point o) {
        return cnt - o.cnt; // 오름차순 정렬
    }
}