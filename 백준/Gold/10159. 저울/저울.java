import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[][] arr, reverse;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1][N + 1];
        reverse = new boolean[N + 1][N + 1];

        M = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u][v] = true;
            reverse[v][u] = true;
        }

        // arr[i][j] vs arr[i][k] + arr[k][j]
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if (k == j || i == j) continue;

                    // 연결 여부만 확인
                    if (arr[i][k] && arr[k][j]) arr[i][j] = true;
                    if (reverse[i][k] && reverse[k][j]) reverse[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int count = N - 1;
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] || reverse[i][j]) count--;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
