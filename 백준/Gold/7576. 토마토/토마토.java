import java.io.*;
import java.util.*;

public class Main {
    static int n, m, tomatoNum = 0;
    static int[][] box;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        box = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (box[i][j] == 0) {
                    tomatoNum++;
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int day = 0;
        int checkTomato = 0;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + move[j][0];
                    int ny = now[1] + move[j][1];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) {
                        continue;
                    }

                    if (box[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        checkTomato++;
                    }
                }
            }
            day++;
        }
        if (checkTomato == tomatoNum) return day - 1; // 보관 후 하루가 지난 뒤부터 토마토가 익음
        return -1;
    }
}