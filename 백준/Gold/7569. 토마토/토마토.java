import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H, tomatoes = 0;
    static int[][][] box;
    static boolean[][][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[N][M][H];
        visited = new boolean[N][M][H];
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) { // 익은 토마토의 위치 큐에 넣기
                        queue.offer(new int[]{i, j, k});
                        visited[i][j][k] = true;
                    } else if (box[i][j][k] == 0) { // 익지 않은 토마토의 수 세기
                        tomatoes++;
                    }
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int day = 0, changeTomatoes = 0;
        int[][] move = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1,}, {0, 0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();

                for (int j = 0; j < 6; j++) {
                    int nx = now[0] + move[j][0];
                    int ny = now[1] + move[j][1];
                    int nz = now[2] + move[j][2];
                    if (nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H) {
                        continue;
                    }

                    if (!visited[nx][ny][nz] && box[nx][ny][nz] == 0) {
                        queue.offer(new int[]{nx, ny, nz});
                        visited[nx][ny][nz] = true;
                        changeTomatoes++;
                    }
                }
            }
            day++;
        }
        if (changeTomatoes == tomatoes) return day - 1;
        return -1;
    }
}