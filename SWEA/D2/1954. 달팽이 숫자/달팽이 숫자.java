import java.io.*;

class Solution
{
    static int n;
    static boolean finish;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            visited = new boolean[n][n];
            finish = false;
            dfs(1, 0, 0);
            sb.append("#").append(t).append("\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int value, int x, int y) {
        if (value > n * n) {
            finish = true;
            return;
        }

        arr[x][y] = value;
        visited[x][y] = true;
        int start = (x <= n / 2) ? 0 : 2;
        for (int i = start, cnt = 0; cnt < 4; cnt++, i = (i + 1) % 4) {
            if (finish) break;
            int nx = x + move[i][0];
            int ny = y + move[i][1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
            dfs(value + 1, nx, ny);
        }
    }
}