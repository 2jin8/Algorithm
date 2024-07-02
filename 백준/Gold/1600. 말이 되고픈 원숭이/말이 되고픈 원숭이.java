import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] board;
    static boolean[][][] visited;
    static int[][] jump = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x, y = now.y;
            if (x == H - 1 && y == W - 1) {
                return now.dist;
            }

            // 우선 원숭이처럼 이동하기
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W || visited[nx][ny][now.jump]) {
                    continue;
                }

                if (board[nx][ny] == 0) { // 평지면 이동 가능
                    queue.offer(new Node(nx, ny, now.jump, now.dist + 1));
                    visited[nx][ny][now.jump] = true;
                }
            }

            // 말처럼 이동할 수 있는 횟수가 남은 경우
            if (now.jump < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + jump[i][0];
                    int ny = y + jump[i][1];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W || visited[nx][ny][now.jump + 1]) {
                        continue;
                    }

                    if (board[nx][ny] == 0) { // 평지면 이동 가능
                        queue.offer(new Node(nx, ny, now.jump + 1, now.dist + 1));
                        visited[nx][ny][now.jump + 1] = true;
                    }
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y;
        int jump; // 말처럼 움직인 횟수
        int dist; // 이동 횟수

        public Node(int x, int y, int jump, int dist) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.dist = dist;
        }
    }
}