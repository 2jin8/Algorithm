import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] cheese;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken()) == 1; // 0: false, 1: true
            }
        }

        int time = 0;
        while (!isMelt()) { // 다 녹으면 true 반환, 덜 녹으면 false 반환
            visited = new boolean[N][M];
            bfs();
            time++;
        }
        System.out.println(time);
    }

     static void bfs() {
         Queue<Pos> queue = new LinkedList<>();
         queue.offer(new Pos(0, 0));
         visited[0][0] = true;

         int[][] melt = new int[N][M];
         int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
         while (!queue.isEmpty()) {
             Pos pos = queue.poll();

             for (int i = 0; i < 4; i++) {
                 int nx = pos.x + move[i][0];
                 int ny = pos.y + move[i][1];
                 if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                     continue;
                 }

                 // 치즈가 있는 칸인 경우
                 if (cheese[nx][ny]) {
                     // 공기와 맞닿으므로 1 증가
                     melt[nx][ny]++;
                 } else { // 치즈가 없는 칸인 경우
                     // 큐에 넣기
                     queue.offer(new Pos(nx, ny));
                     visited[nx][ny] = true;
                 }
             }
         }

         // 4변 중에서 2변 이상이 공기와 접촉한 경우, 치즈가 녹음
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < M; j++) {
                 if (melt[i][j] >= 2) {
                     cheese[i][j] = false;
                 }
             }
         }
    }

    // 치즈가 다 녹았는지 확인하기
    static boolean isMelt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 아직 치즈가 덜 녹은 경우
                if (cheese[i][j]) return false;
            }
        }
        // 치즈가 다 녹은 경우
        return true;
    }

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}