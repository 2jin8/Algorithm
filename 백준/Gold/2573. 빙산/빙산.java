import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] ice, tmpIce;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ice = new int[N][M];
        tmpIce = new int[N][M]; // 다음 연도의 빙하 상태
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(melting());
    }

    static int melting() {
        int year = 0;
        while (true) {
            visited = new boolean[N][M];
            copyArray(ice, tmpIce);
            int callBfs = 0; // bfs 메소드 호출 횟수 (두 번째 호출이라면 빙산이 쪼개진 것)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (ice[i][j] != 0 && !visited[i][j]) {
                        callBfs++;
                        if (callBfs >= 2) return year;

                        bfs(i, j);
                    }
                }
            }
            copyArray(tmpIce, ice);
            year++;
            if (callBfs == 0) return 0; // 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않는 경우
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0]; y = now[1];

            // 네 방향에 바다가 몇 개인지 세기
            int sea = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (ice[nx][ny] == 0) { // 바다인 경우
                    sea++;
                } else if (ice[nx][ny] != 0 && !visited[nx][ny]) { // 다음에 BFS 탐색을 할 빙산인 경우
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
            tmpIce[x][y] = Math.max(0, ice[x][y] - sea);
        }
    }

    static void copyArray(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
}