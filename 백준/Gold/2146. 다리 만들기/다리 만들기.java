import java.io.*;
import java.util.*;

public class Main {
    static int N, minDist = Integer.MAX_VALUE;
    static int[][] map, dist;
    static boolean[][] visited;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 그룹 나누기
        int group = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    makeGroup(i, j, ++group);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    visited = new boolean[N][N];
                    dist = new int[N][N];
                    bfs(i, j);
                }
            }
        }
        System.out.println(minDist);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int group = map[x][y];
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0]; y = now[1];

            // 현재 최소 길이보다 길면 최소 거리가 되지 않으므로 탐색 중단
            if (dist[x][y] > minDist) break;
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                // 범위 벗어나거나 이미 방문했거나 같은 섬을 탐색하려고 하는 경우라면 그냥 넘어가기
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == group) {
                    continue;
                }

                if (map[nx][ny] == 0) { // 바다인 경우
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + 1;
                } else if (map[nx][ny] != map[x][y]) { // 다른 섬에 닿은 경우
                    minDist = Math.min(minDist, dist[x][y]);
                    break;
                }
            }
        }
    }

    static void makeGroup(int x, int y, int group) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = group;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0) {
                    continue;
                }

                if (map[nx][ny] == 1) {
                    map[nx][ny] = group;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}