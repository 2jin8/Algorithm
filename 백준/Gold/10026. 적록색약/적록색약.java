import java.util.*;
import java.io.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 적록색약 X - 빨 / 파 / 초
        // 적록색약 O - 빨, 초 / 파
        n = Integer.parseInt(br.readLine());
        char[][] areaO = new char[n][n]; // 적록색약 O
        char[][] areaX = new char[n][n]; // 적록색약 X
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                areaX[i][j] = str.charAt(j);
                if (areaX[i][j] == 'B') areaO[i][j] = areaX[i][j];
                else areaO[i][j] = 'R'; // R, G -> R로 저장
            }
        }

        boolean[][] visitedO = new boolean[n][n];
        boolean[][] visitedX = new boolean[n][n];
        int cntO = 0, cntX = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 적록색약 O
                if (!visitedO[i][j]) {
                    bfs(areaO, visitedO, i, j);
                    cntO++;
                }
                // 적록색약 X
                if (!visitedX[i][j]) {
                    bfs(areaX, visitedX, i, j);
                    cntX++;
                }
            }
        }
        System.out.println(cntX + " " + cntO);
    }

    public static void bfs(char[][] area, boolean[][] visited, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        char color = area[x][y];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = poll[0] + dx[i];
                int ty = poll[1] + dy[i];
                if (tx < 0 || ty < 0 || tx >= n || ty >= n)
                    continue;

                if (area[tx][ty] == color && !visited[tx][ty]) {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                }
            }
        }
    }
}