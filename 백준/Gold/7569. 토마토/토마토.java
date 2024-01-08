import java.util.*;
import java.io.*;

public class Main {

    static int M, N, H;
    static int[][][] box;
    static boolean[][][] visited;
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 위, 아래, 상하좌우,
        // 3차원
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[N][M][H];
        visited = new boolean[N][M][H];
        int tomato = 0, empty = 0;
        for (int l = 0; l < H; l++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    box[i][j][l] = Integer.parseInt(st.nextToken());
                    if (box[i][j][l] == 1) {
                        tomato++;
                        queue.offer(new Point(i, j, l));
                        visited[i][j][l] = true;
                    }
                    else if (box[i][j][l] == -1) empty++;
                }
            }
        }
        if (tomato + empty == N * H * M) {
            System.out.println(0);
            return;
        }

        int day = bfs();
        for (int l = 0; l < H; l++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 빈 박스가 아닌데 방문하지 않은 경우, 토마토가 모두 익지 못하는 상황임
                    if (box[i][j][l] != -1 && !visited[i][j][l]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(day);
    }

    public static int bfs() {
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};
        int day = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size != 0) day++;
            for (int s = 0; s < size; s++) {
                Point point = queue.poll();

                for (int i = 0; i < 6; i++) {
                    int tx = point.x + dx[i];
                    int ty = point.y + dy[i];
                    int tz = point.z + dz[i];
                    if (tx < 0 || ty < 0 || tz < 0 || tx >= N || ty >= M || tz >= H)
                        continue;

                    if (!visited[tx][ty][tz] && box[tx][ty][tz] != -1) {
                        queue.offer(new Point(tx, ty, tz));
                        visited[tx][ty][tz] = true;
                    }
                }
            }
        }
        return day - 1;
    }
}

class Point {
    int x;
    int y;
    int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}