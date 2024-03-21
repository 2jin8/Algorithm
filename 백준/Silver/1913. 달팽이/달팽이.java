import java.io.*;

public class Main {
    static int n, k, m, num = 1;
    static int[][] map;
    static Pos pos;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];
        m = n / 2;
        map[m][m] = num++; // 중앙부터 시작
        if (map[m][m] == k) pos = new Pos(m + 1, m + 1);
        dfs(m - 1, m); // 중앙에서는 위로 이동해서 DFS 탐색 시작
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(pos.x).append(" ").append(pos.y);
        System.out.println(sb);
    }

    public static Pos[] getPos(int x, int y) { // 구역에 따라 이동하는 방향의 우선순위가 다름
        Pos[] pos = new Pos[2];
        if (x <= m) {
            if (y <= m) {
                pos[0] = new Pos(0, 1);
                pos[1] = new Pos(-1, 0);
            } else {
                pos[0] = new Pos(1, 0);
                pos[1] = new Pos(0, 1);
            }
        } else {
            if (y <= m) {
                pos[0] = new Pos(-1, 0);
                pos[1] = new Pos(0, -1);
            } else {
                pos[0] = new Pos(0,-1);
                pos[1] = new Pos(1, 0);
            }
        }
        return pos;
    }

    public static void dfs(int x, int y) {
        if (num > n * n) return;
        map[x][y] = num++;
        if (map[x][y] == k) pos = new Pos(x + 1, y + 1);

        int start;
        if (x <= m) {
            if (y <= m) start = 0;
            else start = 3;
        } else {
            if (y <= m) start = 1;
            else start = 2;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[(start + i) % 4];
            int ny = y + dy[(start + i) % 4];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (map[nx][ny] == 0) {
                dfs(nx, ny);
                return;
            }
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}