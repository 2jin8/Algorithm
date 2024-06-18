import java.io.*;
import java.util.*;

public class Main {
    static int l, startX, startY, endX, endY;
    static int[][] board;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            l = Integer.parseInt(br.readLine());
            board = new int[l][l];
            visited = new boolean[l][l];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            bfs();
            sb.append(board[endX][endY]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        int[][] move = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1];
            if (x == endX && y == endY) break;

            for (int i = 0; i < 8; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= l || ny >= l || visited[nx][ny]) {
                    continue;
                }

                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                board[nx][ny] = board[x][y] + 1;
            }
        }
    }
}