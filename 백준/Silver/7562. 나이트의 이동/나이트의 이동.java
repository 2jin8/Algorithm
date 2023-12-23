import java.io.*;
import java.util.*;

public class Main {
    static int l;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 최소 몇 번만에 이동 -> BFS 사용
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            l = Integer.parseInt(br.readLine());
            board = new int[l][l];
            String[] start = br.readLine().split(" ");
            String[] end = br.readLine().split(" ");
            Point sPoint = new Point(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
            Point ePoint = new Point(Integer.parseInt(end[0]), Integer.parseInt(end[1]));
            bw.write(bfs(sPoint, ePoint) + "\n");
        }
        bw.flush(); bw.close();
    }

    public static int bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        board[start.x][start.y] = 1;

        int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
        int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == end.x && point.y == end.y)
                break;

            for (int i = 0; i < 8; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];

                if (tx < 0 || ty < 0 || tx >= l || ty >= l)
                    continue;

                if (board[tx][ty] == 0) {
                    queue.offer(new Point(tx, ty));
                    board[tx][ty] = board[point.x][point.y] + 1;
                }
            }
        }
        return board[end.x][end.y] - 1;
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