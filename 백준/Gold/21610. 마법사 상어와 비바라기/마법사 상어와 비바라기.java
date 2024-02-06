import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] bucket;
    static boolean[][] cloud, tmp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bucket = new int[N][N];
        cloud = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                bucket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비바라기 시전 → 구름 생성(N-1,0), (N-1,1), (N-2,0), (N-2,1) 구름 생김
        for (int i = N - 2; i < N; i++) {
            cloud[i][0] = true;
            cloud[i][1] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            move(d - 1, s);
        }
        int water = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                water += bucket[i][j];
            }
        }
        System.out.println(water);
    }

    public static void move(int d, int s) {
        // d 방향으로 이동하기 위한 배열
        int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        ArrayList<int[]> list = new ArrayList<>();
        boolean[][] tmp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j]) { // 구름이 있으면
                    // 구름 이동하기 (연결되어 있으므로 %N 필수!)
                    cloud[i][j] = false;
                    int tx = getPos(i + dx[d] * s);
                    int ty = getPos(j + dy[d] * s);
                    // 비 내림 - 바구니 값 증가
                    bucket[tx][ty]++;
                    tmp[tx][ty] = true;
                    list.add(new int[]{tx, ty});
                }
            }
        }
        // 모든 구름 사라짐
        cloud = new boolean[N][N];

        // 대각선 이동을 위한 배열
        int[] cx = {-1, -1, 1, 1};
        int[] cy = {-1, 1, -1, 1};
        // 물이 증가한 칸에 물 복사 버그
        // 대각선에 위치하는 바구니 "물이 있는 바구니의 수만큼" 증가 - 아 잘못 이해함..
        for (int i = 0; i < list.size(); i++) {
            int[] p = list.get(i);
            int water = 0;
            for (int j = 0; j < 4; j++) {
                int tx = p[0] + cx[j];
                int ty = p[1] + cy[j];
                if (tx < 0 || ty < 0 || tx >= N || ty >= N) continue;
                if (bucket[tx][ty] != 0) water++;
            }
            bucket[p[0]][p[1]] += water;
        }
        // 구름 생성하기 (단, 구름이 생기는 칸은 구름이 사라진 칸이 아니여야 함)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!tmp[i][j] && bucket[i][j] >= 2) {
                    bucket[i][j] -= 2;
                    cloud[i][j] = true;
                }
            }
        }
    }

    public static int getPos(int pos) {
        if (pos < 0) {
            while (pos < 0) pos += N;
        }
        return pos % N;
    }
}