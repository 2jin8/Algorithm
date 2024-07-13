import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 최소의 칸 수 구하기 = BFS (단, )
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 1; // 시작 위치와 도착 위치도 칸을 셀 때 포함하기

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1];
            if (x == N - 1 && y == M - 1) break;

            // 네 방향으로 탐색하기
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                // 범위를 벗어나면 탐색 X
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // 이미 방문하지 않았고 이동할 수 있는 칸(=1)인 경우
                if (dist[nx][ny] == 0 && map[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                    dist[nx][ny] = dist[x][y] + 1;
                }
            }
        }
        return dist[N - 1][M - 1];
    }
}