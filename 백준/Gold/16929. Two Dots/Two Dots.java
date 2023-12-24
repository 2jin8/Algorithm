import java.io.*;
import java.util.*;

public class Main {
    static int n, m, startX, startY, cycle;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        cycle = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited = new boolean[n][m];
                startX = i; startY = j;
                dfs(i, j, 1);
                if (cycle == 1) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    public static void dfs(int x, int y, int cnt) {
        // DFS 탐색해서 원래 위치로 돌아오면 사이클 존재
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                continue;

            if (board[tx][ty] != board[x][y])
                continue;

            if (visited[tx][ty] && tx == startX && ty == startY && cnt >= 4) {
                cycle = 1;
                return;
            }

            if (!visited[tx][ty]) {
                visited[tx][ty] = true;
                dfs(tx, ty, cnt + 1);
                if (cycle == 1) return;
            }
        }
    }
}