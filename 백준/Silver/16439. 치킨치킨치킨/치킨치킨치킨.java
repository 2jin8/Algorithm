import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] favor;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        favor = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                favor[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < m - 2; i++) {
            for (int j = i + 1; j < m - 1; j++) {
                for (int k = j + 1; k < m; k++) {
                    ans = Math.max(ans, findMax(i, j, k));
                }
            }
        }
        System.out.println(ans);
    }

    public static int findMax(int a, int b, int c) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.max(favor[i][a], Math.max(favor[i][b], favor[i][c]));
        }
        return total;
    }
}