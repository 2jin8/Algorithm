import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] pictureO, pictureX; // O: 적록색약 O, X: 적록색약 X
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pictureO = new char[N][N]; // R == G
        pictureX = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                pictureO[i][j] = line.charAt(j);
                pictureX[i][j] = line.charAt(j);
                if (pictureO[i][j] == 'G') pictureO[i][j] = 'R';
            }
        }

        int totalO = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    totalO++;
                    bfs(i, j, pictureO);
                }
            }
        }

        int totalX = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    totalX++;
                    bfs(i, j, pictureX);
                }
            }
        }
        System.out.println(totalX + " " + totalO);
    }

    static void bfs(int x, int y, char[][] picture) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }

                if (picture[nx][ny] == picture[x][y]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}