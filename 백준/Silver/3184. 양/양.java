import java.util.*;
import java.io.*;

public class Main {
    static int R, C, sheep, fox, areaSheep, areaFox;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'v') fox++;
                else if (map[i][j] == 'o') sheep++;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '#' && !visited[i][j]) {
                    areaFox = 0; areaSheep = 0;
                    dfs(i, j);
                    if (areaSheep > areaFox) fox -= areaFox; // 양이 이긴 경우
                    else sheep -= areaSheep; // 늑대가 이긴 경우
                }
            }
        }
        System.out.println(sheep + " " + fox);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        if (map[x][y] == 'o') areaSheep++; // 영역 내의 양의 수 세기
        else if (map[x][y] == 'v') areaFox++; // 영역 내의 늑대 수 세기

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]) {
                continue;
            }

            if (map[nx][ny] != '#') { // 벽이 아니면 이동 가능
                dfs(nx, ny);
            }
        }
    }
}