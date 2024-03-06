import java.io.*;
import java.util.*;

public class Main {
    static int W, H, total = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[][] odd = {{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, 1}};
    static int[][] even = {{-1, -1}, {-1, 0,}, {0, -1}, {0, 1}, {1, -1}, {1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken()) + 2; // 벽이 아닌 부분(=0인 부분)을 탐색하기 위해 칸 늘리기
        H = Integer.parseInt(st.nextToken()) + 2;
        map = new int[H][W];
        visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            if (i == 0 || i == H - 1) {
                Arrays.fill(map[i], 0);
                continue;
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                if (j == 0 || j == W - 1) {
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);
        System.out.println(total);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0]; y = poll[1];

            boolean e = (x % 2 == 0);
            for (int i = 0; i < 6; i++) {
                int nx = x + (e ? even[i][0] : odd[i][0]); // 홀수, 짝수번째 나눠서 계산하기
                int ny = y + (e ? even[i][1] : odd[i][1]);
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                if (map[nx][ny] == 1) { // 벽과 닿은 경우 total 값 1 더하기
                    total++;
                    continue;
                }

                if (!visited[nx][ny]) { // 방문하지 않았고 벽이 아닌 경우, 큐에 넣기
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}