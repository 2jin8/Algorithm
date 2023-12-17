import java.util.*;
import java.io.*;

public class Main {
    private static int N, M;
    private static int wNum = 0, bNum = 0;
    private static char[][] board;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    if (board[i][j] == 'W') wNum += Math.pow(bfs(i, j), 2);
                    else bNum += Math.pow(bfs(i, j), 2);
                }
            }
        }
        System.out.println(wNum + " " + bNum);
    }

    public static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        char team = board[x][y];
        queue.offer(new Point(x, y));
        visited[x][y] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int num = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            num++;

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];

                if (tx < 0 || ty < 0 || tx >= M || ty >= N)
                    continue;

                if (board[tx][ty] == team && !visited[tx][ty]) { // 같은 팀이고 방문하지 않은 경우
                    queue.offer(new Point(tx, ty));
                    visited[tx][ty] = true;
                }
            }
        }
        return num;
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