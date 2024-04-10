import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int white = 0, blue = 0, area;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    area = 0;
                    dfs(i, j, map[i][j]);
                    area = (int) Math.pow(area, 2);
                    if (map[i][j] == 'W') white += area;
                    else blue += area;
                }
            }
        }
        System.out.println(white + " " + blue);
    }

    public static void dfs(int x, int y, char color) {
        area++;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny]) {
                continue;
            }

            if (map[nx][ny] == color) {
                dfs(nx, ny, color);
            }
        }
    }
}