import java.util.*;
import java.io.*;

public class Main {
    static int n, depth;
    static char[][] house;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                house[i][j] = str.charAt(j);
            }
        }

        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && house[i][j] == '1') {
                    depth = 0;
                    dfs(i, j);
                    results.add(depth);
                }
            }
        }
        System.out.println(results.size());
        results.stream().sorted().forEach(System.out::println);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        depth++;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= n || ty >= n)
                continue;

            if (!visited[tx][ty] && house[tx][ty] == '1') dfs(tx, ty);
        }
    }
}