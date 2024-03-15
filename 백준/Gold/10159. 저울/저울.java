import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static final int INF = (int) 1e9;
    static int[][] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        arr2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(arr1[i], INF);
            Arrays.fill(arr2[i], INF);
            arr1[i][i] = 0;
            arr2[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            // 단방향 (a → b)
            arr1[a][b] = 1;
            // 역방향 (b → a)
            arr2[b][a] = 1;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Math.min(arr1[i][j], arr1[i][k] + arr1[k][j]);
                    arr2[i][j] = Math.min(arr2[i][j], arr2[i][k] + arr2[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int total = N - 1;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (arr1[i][j] != INF) total--;
                if (arr2[i][j] != INF) total--;
            }
            sb.append(total).append("\n");
        }
        System.out.println(sb.toString());
    }
}