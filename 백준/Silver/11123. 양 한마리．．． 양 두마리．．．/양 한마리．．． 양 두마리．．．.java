import java.io.*;
import java.util.*;

public class Main {
    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String[] str = br.readLine().split(" ");
            H = Integer.parseInt(str[0]);
            W = Integer.parseInt(str[1]);
            map = new char[H][W];
            visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            int ans = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == '#' && !visited[i][j]) {
                        ans++;
                        bfs(i, j);
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = poll[0] + dx[i];
                int ty = poll[1] + dy[i];
                if (tx < 0 || ty < 0 || tx >= H || ty >= W)
                    continue;

                if (!visited[tx][ty] && map[tx][ty] == '#') {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                }
            }
        }
    }
}