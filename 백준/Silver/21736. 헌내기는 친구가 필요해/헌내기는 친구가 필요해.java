import java.io.*;
import java.util.*;

public class Main {

    static int n, m, pCount = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        String[][] campus = new String[n][m];
        boolean[][] visited = new boolean[n][m];
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            String strings = br.readLine();
            for (int j = 0; j < m; j++) {
                String string = String.valueOf(strings.charAt(j));
                campus[i][j] = string;
                if (string.equals("I")) { // 도연이의 위치 기록
                    x = i; y = j;
                }
            }
        }

        dfs(campus, visited, x, y);
        if (pCount == 0)
            System.out.println("TT");
        else System.out.println(pCount);
    }

    public static void dfs(String[][] campus, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        if (campus[x][y].equals("P")) { // 사람을 만난 경우
            pCount++;
        }

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                continue;

            if (!visited[tx][ty] && !campus[tx][ty].equals("X"))
                dfs(campus, visited, tx, ty);
        }
    }
}