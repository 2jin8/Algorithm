import java.io.*;
import java.util.*;

public class Main {
    static int H, W, total = 0, time = 0;
    static boolean flag = true;
    static int[][] map, arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) total++;
            }
        }
        
        while (flag) {
            visited = new boolean[H][W];
            time++;
            flag = bfs(0, 0);
        }
        System.out.println(time);
        System.out.println(total);
    }

    public static boolean bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        arr = map.clone(); // 한 시간 후의 판 상태
        int cnt = 0; // 녹은 치즈의 수
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                if (visited[nx][ny]) continue;

                if (map[nx][ny] == 1) { // 치즈가 공기와 맞닿은 경우
                    arr[nx][ny] = 0; // 치즈가 녹음
                    visited[nx][ny] = true;
                    cnt++;
                } else { // 치즈가 없으면 큐에 넣어서 탐색하기
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        map = arr.clone();
        if (total == cnt) return false;
        total -= cnt;
        return true;
    }
}