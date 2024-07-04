import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x, y = now.y;
            if (x == N - 1 && y == M - 1) {
                return now.dist + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (map[nx][ny] == 1) { // 이동하려는 지점이 벽인 경우
                    if (now.crash < K && !visited[nx][ny][now.crash + 1]) {
                        queue.offer(new Node(nx, ny, now.crash + 1, now.dist + 1));
                        visited[nx][ny][now.crash + 1] = true;
                    }
                } else if (map[nx][ny] == 0 && !visited[nx][ny][now.crash]) {
                    queue.offer(new Node(nx, ny, now.crash, now.dist + 1));
                    visited[nx][ny][now.crash] = true;
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y;
        int crash; // 벽을 부순 횟수
        int dist; // 이동 횟수

        public Node(int x, int y, int crash, int dist) {
            this.x = x;
            this.y = y;
            this.crash = crash;
            this.dist = dist;
        }
    }
}