import java.util.*;
import java.io.*;

public class Main {
    private static int R, C;
    private static int sheep, fox, sheepNum, foxNum;
    private static char[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'o') sheep++;
                if (board[i][j] == 'v') fox++;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != '#' && !visited[i][j]) { // 울타리가 아니고 방문하지 않은 경우
                    sheepNum = 0;
                    foxNum = 0;
                    dfs(i, j);
                    if (sheepNum > foxNum) fox -= foxNum;
                    else sheep -= sheepNum;
                }
            }
        }
        System.out.println(sheep + " " + fox);
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;
        if (board[r][c] == 'o') sheepNum++;
        else if (board[r][c] == 'v') foxNum++;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int tr = r + dr[i];
            int tc = c + dc[i];

            if (tr < 0 || tc < 0 || tr >= R || tc >= C) // 범위 벗어나는 경우
                continue;

            if (board[tr][tc] == '#') // 울타리인 경우
                continue;

            if (!visited[tr][tc]) {
                dfs(tr, tc);
            }
        }
    }
}