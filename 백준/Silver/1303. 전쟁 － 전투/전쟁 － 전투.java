import java.util.*;
import java.io.*;

public class Main {
    private static int N, M;
    private static int wNum = 0, bNum = 0, num;
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
                    num = 0;
                    dfs(i, j, board[i][j]);
                    if (board[i][j] == 'W') wNum += Math.pow(num, 2);
                    else bNum += Math.pow(num, 2);
                }
            }
        }
        System.out.println(wNum + " " + bNum);
    }

    public static void dfs(int x, int y, char team) {
        visited[x][y] = true;
        num++;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx < 0 || ty < 0 || tx >= M || ty >= N)
                continue;

            if (board[tx][ty] == team && !visited[tx][ty]) {
                dfs(tx, ty, team);
            }
        }
    }
}