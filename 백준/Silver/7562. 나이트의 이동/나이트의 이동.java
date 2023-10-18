import java.util.*;

public class Main {

    private static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static boolean[][] visited;
    private static int[][] board;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int l = scan.nextInt();
            visited = new boolean[l][l];
            board = new int[l][l];
            Point start = new Point(scan.nextInt(), scan.nextInt());
            Point end = new Point(scan.nextInt(), scan.nextInt());
            System.out.println(bfs(start, end, l));
        }
    }

    private static int bfs(Point start, Point end, int l) {
        visited[start.x][start.y] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            if (x == end.x && y == end.y) {
                break;
            }

            for (int i = 0; i < 8; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (tx < 0 || ty < 0 || tx >= l || ty >= l)
                    continue;

                if (!visited[tx][ty]) {
                    queue.add(new Point(tx, ty));
                    visited[tx][ty] = true;
                    board[tx][ty] = board[x][y] + 1;
                }
            }
        }
        return board[end.x][end.y];
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