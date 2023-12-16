import java.util.*;
import java.io.*;

public class Main {
    private static int n, m, k, maxCnt = 0;
    private static int[][] board;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        Point[] points = new Point[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 1;
            points[i] = new Point(r, c);
        }

        visited = new boolean[n + 1][m + 1];
        for (int i = 0; i < k; i++) { // 음식물 존재하는 위치 탐색
            Point point = points[i];
            if (!visited[point.r][point.c]) {
                bfs(point.r, point.c);
            }
        }
        System.out.println(maxCnt);
    }

    public static void bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int cnt = 0; // 연속된 음식물 크기
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            r = point.r;
            c = point.c;
            cnt++;

            for (int i = 0; i < 4; i++) {
                int tr = r + dr[i];
                int tc = c + dc[i];

                if (tr <= 0 || tc <= 0 || tr > n || tc > m)
                    continue;

                if (board[tr][tc] == 0)
                    continue;

                if (!visited[tr][tc]) {
                    queue.offer(new Point(tr, tc));
                    visited[tr][tc] = true;
                }
            }
        }
        maxCnt = Math.max(maxCnt, cnt);
    }
}

class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}