import java.io.*;
import java.util.*;

public class Main {
    static int n, answer = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[][] visited;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int cnt, int sum) {
        if (cnt == 3) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (visited[i][j]) continue;

                Pos[] pos = new Pos[4];
                boolean isOut = false;
                int total = board[i][j];
                for (int k = 0; k < 4; k++) {
                    int nx = i + move[k][0];
                    int ny = j + move[k][1];
                    // 격자를 벗어나거나 겹치는 꽃이 있다면 탐색 종료
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) {
                        isOut = true;
                        break;
                    }
                    // 아니면 위치 기록
                    pos[k] = new Pos(nx, ny);
                    total += board[nx][ny];
                }
                if (isOut) continue;
                
                visited[i][j] = true;
                for (Pos p : pos) {
                    visited[p.x][p.y] = true;
                }
                dfs(cnt + 1, sum + total);
                visited[i][j] = false;
                for (Pos p : pos) {
                    visited[p.x][p.y] = false;
                }
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