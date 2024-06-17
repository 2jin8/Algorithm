import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] board, dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(bfs(0, 0));
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        dist[x][y] = 1; // 칸을 셀 때 시작 위치도 포함

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];
            if (x == n - 1 && y == m - 1) {
                // 칸을 셀 때 도착 위치도 포함 ⇒ 기록된 값 그래도 출력하면 됨
                break; // 항상 도착위치로 이동할 수 있는 경우만 주어짐
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                // 범위 벗어나거나 이미 방문한 위치면 넘어가기
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || dist[nx][ny] != 0) {
                    continue;
                }

                if (board[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                    dist[nx][ny] = dist[x][y] + 1;
                }
            }
        }
        return dist[n - 1][m - 1];
    }
}