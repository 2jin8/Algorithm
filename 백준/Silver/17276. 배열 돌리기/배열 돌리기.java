import java.util.*;
import java.io.*;

public class Main {
    static int T, n, d, mid;
    static int[][] arr, rotate;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            mid = (n + 1) / 2 - 1; // 중간값
            arr = new int[n][n];
            rotate = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            arrCopy(rotate, arr);

            if (d % 360 != 0) { // 각도가 0 또는 360이면 원래 위치로 돌아옴
                rotate();
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void rotate() {
        int rotateCnt = Math.abs(d) / 45;
        for (int i = 0; i < rotateCnt; i++) {
            if (d < 0) rotateLeft();
            else rotateRight();

            arrCopy(arr, rotate); // 이동한 것 복사하기
        }
    }

    public static void rotateLeft() { // 반시계 방향 회전
        for (int i = 0; i < n; i++) {
            rotate[mid][i] = arr[i][i]; // 주 대각선 회전
            rotate[n - 1 - i][i] = arr[mid][i]; // 가운데 행 회전
            rotate[i][mid] = arr[i][n - 1 - i]; // 부 대각선 회전
            rotate[i][i] = arr[i][mid]; // 가운데 열 회전
        }
    }

    public static void rotateRight() { // 시계 방향 회전
        for (int i = 0; i < n; i++) {
            rotate[i][mid] = arr[i][i]; // 주 대각선 회전
            rotate[i][n - 1 - i] = arr[i][mid]; // 가운데 열 회전
            rotate[mid][i] = arr[n - 1 - i][i]; // 부 대각선 회전
            rotate[i][i] = arr[mid][i]; // 가운데 행 회전
        }
    }


    public static void arrCopy(int[][] target, int[][] origin) { // 2차원 배열 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                target[i][j] = origin[i][j];
            }
        }
    }
}