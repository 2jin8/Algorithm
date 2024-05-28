import java.io.*;
import java.util.*;

/**
 */
public class Main {
    static int N, M, R;
    static int[][] originArr, rotateArr;
    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        originArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                originArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int k = Math.min(N, M) / 2;
        for (int i = 0; i < R; i++) { // 회전 횟수
            rotateArr = new int[N][M];
            for (int j = 0; j < k; j++) {
                rotate(j);
            }
            copyArr(rotateArr, originArr);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(originArr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void rotate(int start) {
        int x = start, y = start, idx = 0;
        while (true) {
            // 옮길 좌표
            int nx = x + move[idx][0];
            int ny = y + move[idx][1];

            // 확인한 지점이 범위를 벗어나거나 이미 기록된 곳이면 방향 틀기
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || rotateArr[nx][ny] != 0) {
                idx++;
                nx = x + move[idx][0];
                ny = y + move[idx][1];
            }

            rotateArr[nx][ny] = originArr[x][y];
            if (nx == start && ny == start) break;
            x = nx; y = ny;
        }

    }

    static void copyArr(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
}