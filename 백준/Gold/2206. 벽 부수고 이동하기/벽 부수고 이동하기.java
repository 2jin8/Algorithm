import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 0: 이동 O, 1: 이동 X
        // (1, 1)에서 (N, M)까지 최단 경로로 이동
        // 벽을 한 개까지 부술 수 있음 ⇒ 상태가 도중에 변경될 수 있음
        // 변할 수 있는 상태: 벽을 부순 경우 vs 벽을 부수지 않은 경우 → 총 2가지,,
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2]; // [][][0]: 벽을 부수지 않은 경우, [][][1]: 벽을 부순 경우
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(0, 0, 1, false));
        visited[0][0][0] = true;

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            if (pos.x == n - 1 && pos.y == m - 1) {
                return pos.time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = pos.x + move[i][0];
                int ny = pos.y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (pos.crashed) { // 이미 벽을 부순 경우
                    if (map[nx][ny] == 0 && !visited[nx][ny][1]) { // 벽이 아닌 경우만 이동 가능
                        queue.offer(new Pos(nx, ny, pos.time + 1, true));
                        visited[nx][ny][1] = true;
                    }
                } else { // 벽을 부수지 않은 경우
                    if (map[nx][ny] == 0 && !visited[nx][ny][0]) { // 이동할 칸이 벽이 아니면 그냥 이동
                        queue.offer(new Pos(nx, ny, pos.time + 1, false));
                        visited[nx][ny][0] = true;
                    } else if (map[nx][ny] == 1 && !visited[nx][ny][1]) { // 이동한 칸이 벽이면 부숴보기
                        queue.offer(new Pos(nx, ny, pos.time + 1, true));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        return -1;
    }

    static class Pos {
        int x, y;
        int time; // 소요 시간
        boolean crashed; // 벽 부쉈는지 여부

        public Pos(int x, int y, int time, boolean crashed) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.crashed = crashed;
        }
    }
}