import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] arr, tmp;
    static int x1, x2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        ArrayList<Integer> points = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    points.add(i);
                }
            }
        }
        x1 = points.get(0);
        x2 = points.get(1);

        for (int i = 0; i < T; i++) {
            tmp = new int[R][C]; // 필수..!
            spreadDust();
            move();
        }
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                total += arr[i][j];
            }
        }
        System.out.println(total);
    }

    public static void spreadDust() {
        // 미세먼지 확산
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    int dust = arr[i][j] / 5;
                    int spread = 0;
                    for (int k = 0; k < 4; k++) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || ty < 0 || tx >= R || ty >= C) continue;

                        if ((tx == x1 && ty == 0) || (tx == x2 && ty == 0)) continue;

                        tmp[tx][ty] += dust;
                        spread++;
                    }
                    tmp[i][j] += (arr[i][j] - dust * spread);
                }
            }
        }
        for (int i = 0; i < R; i++) {
            arr[i] = tmp[i].clone();
        }
    }

    public static void move() {
        for (int i = 0; i <= x1; i++) {
            for (int j = 0; j < C; j++) {
                if (i == x1 && j == 0) tmp[i][j + 1] = 0;
                else if (j == 0) { // 아래
                    if (i + 1 < x1) {
                        tmp[i + 1][j] = arr[i][j];
                    }
                } else if (i == 0) { // 왼쪽
                    tmp[i][j - 1] = arr[i][j];
                } else if (j == C - 1) { // 위
                    if (i - 1 >= 0) {
                        tmp[i - 1][j] = arr[i][j];
                    }
                }  else if (i == x1) { // 오른쪽
                    if (j + 1 < C) {
                        tmp[i][j + 1] = arr[i][j];
                    }
                }
            }
        }
        for (int i = x2; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == x2 && j == 0) tmp[i][j + 1] = 0;
                else if (j == 0) { // 위
                    if (i - 1 > x2) tmp[i - 1][j] = arr[i][j];
                } else if (i == R - 1) { // 왼쪽
                    tmp[i][j - 1] = arr[i][j];
                } else if (j == C - 1) { // 아래
                    tmp[i + 1][j] = arr[i][j];
                } else if (i == x2) { // 오른쪽
                    if (j + 1 < C) tmp[i][j + 1] = arr[i][j];
                }
            }
        }
        for (int i = 0; i < R; i++) {
            arr[i] = tmp[i].clone();
        }
    }
}