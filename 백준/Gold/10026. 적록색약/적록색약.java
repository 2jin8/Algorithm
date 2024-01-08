import java.io.*;

public class Main {
    static int n;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
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
                    dfs(areaO, visitedO, i, j);
                    cntO++;
                }
                // 적록색약 X
                if (!visitedX[i][j]) {
                    dfs(areaX, visitedX, i, j);
                    cntX++;
                }
            }
        }
        System.out.println(cntX + " " + cntO);
    }

    public static void dfs(char[][] area, boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= n || ty >= n)
                continue;

            if (area[x][y] == area[tx][ty] && !visited[tx][ty])
                dfs(area, visited, tx, ty);
        }
    }
}