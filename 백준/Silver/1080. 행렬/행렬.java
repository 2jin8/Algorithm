import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] A = new int[n][m];
        int[][] B = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                A[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                B[i][j] = str.charAt(j) - '0';
            }
        }

        if (isEqual(A, B)) { // 행렬 A와 B가 같은 경우
            System.out.println(0);
            return;
        } else if (n < 3 && m < 3) { // 3 by 3 이하인 행렬
            System.out.println(-1);
            return;
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j] && i + 2 < n && j + 2 < m) {
                    reverse(A, i, j);
                    cnt++;
                    if (isEqual(A, B)) {
                        System.out.println(cnt);
                        return;
                    }
                }
            }
        }
        System.out.println(-1);

    }

    private static void reverse(int[][] A, int i, int j) {
        for (int x = i; x <= i + 2; x++) {
            for (int y = j; y <= j + 2; y++) {
                A[x][y] = (A[x][y] == 0) ? 1 : 0;
            }
        }
    }

    private static boolean isEqual(int[][] A, int[][] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j])
                    return false;
            }
        }
        return true;
    }
}
