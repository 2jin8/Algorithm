import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] mapA = new int[N][M]; // 변경 전 배열
        int[][] mapB = new int[N][M]; // 변경 후 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mapA[i][j] = Integer.parseInt(st.nextToken());
                mapB[i][j] = mapA[i][j];
            }
        }

        int year = 0;
        while (true) { // 빙산이 분리되어있지 않을 때까지
            year++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (mapA[i][j] != 0) { // 빙산이 있다면
                        int zero = 0;
                        for (int k = 0; k < 4; k++) { // 동서남북에 0이 저장된 개수 세기
                            int tx = i + dx[k];
                            int ty = j + dy[k];
                            if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                            if (mapA[tx][ty] == 0) zero++;
                        }
                        mapB[i][j] = Math.max(0, mapB[i][j] - zero); // 주변에 0의 개수만큼 감소
                    }
                }
            }

            int total = check(mapB);
            if (total == 0) { // 빙산이 다 녹은 경우
                System.out.println(0);
                return;
            } else if (total != 1) {
                System.out.println(year);
                break;
            }

            for (int i = 0; i < N; i++) {
                mapA[i] = mapB[i].clone();
            }
        }
    }

    public static int check(int[][] map) {
        boolean[][] visited = new boolean[N][M];
        int total = 0;
        // 빙산이 분리되어 있는지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    total++;
                    bfs(map, visited, i, j);
                }
            }
        }

        return total;
    }

    public static void bfs(int[][] map, boolean[][] visited, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = poll[0] + dx[i];
                int ty = poll[1] + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                if (!visited[tx][ty] && map[tx][ty] != 0) {
                    visited[tx][ty]=true;
                    queue.offer(new int[]{tx, ty});
                }
            }
        }
    }
}