import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 그룹 나누기
        visited = new boolean[N][N];
        int group = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    bfs(i, j, ++group);
                }
            }
        }

        // 각 그룹에서 다른 그룹까지 최단 거리 구하기
        int minV = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    minV = Math.min(minV, findRoute(i, j));
                }
            }
        }
        System.out.println(minV);
    }

    public static int findRoute(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[N][N];
        visited = new boolean[N][N];
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int group = map[x][y], min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0];
            y = poll[1];

            if (dist[x][y] > min) continue; // 현재 최단 거리보다 길면 탐색 X
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= N) continue;

                // 다른 육지 만난 경우
                if (map[tx][ty] != 0 && map[tx][ty] != group) {
                    min = Math.min(min, dist[x][y]);
                    continue;
                }

                if (map[tx][ty] == 0 && !visited[tx][ty]) {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                    dist[tx][ty] = dist[x][y] + 1;
                }
            }
        }
        return min;
    }

    public static void bfs(int x, int y, int value) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = value;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = poll[0] + dx[i];
                int ty = poll[1] + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= N) continue;

                if (!visited[tx][ty] && map[tx][ty] != 0) {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                    map[tx][ty] = value;
                }
            }
        }
    }
}