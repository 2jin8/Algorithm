import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // [][][0]: 벽 부수기 X, [][][1]: 벽 부수기 O
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(0, 0, 1, false));
        visited[0][0][0] = true;

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            int x = pos.x, y = pos.y, dist = pos.dist;
            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // 벽을 부순 경우
                if (pos.crashed) {
                    // 벽이 아닌 곳으로만 이동해야 함
                    if (map[nx][ny] == 0 && !visited[nx][ny][1]) {
                        queue.offer(new Pos(nx, ny, dist + 1, true));
                        visited[nx][ny][1] = true;
                    }
                } else { // 벽을 부수지 않은 경우
                    if (map[nx][ny] == 1 && !visited[nx][ny][1]) { // 이동하려는 곳이 벽인 경우
                        // 벽 부수고 이동하기
                        queue.offer(new Pos(nx, ny, dist + 1, true));
                        visited[nx][ny][1] = true;
                    } else if (map[nx][ny] == 0 && !visited[nx][ny][0]) { // 이동하려는 곳이 벽이 아닌 경우
                        queue.offer(new Pos(nx, ny, dist + 1, false));
                        visited[nx][ny][0] = true;
                    }

                }
            }
        }
        return -1;
    }

    static class Pos {
        int x, y;
        int dist;
        boolean crashed;

        public Pos(int x, int y, int dist, boolean crashed) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.crashed = crashed;
        }
    }
}