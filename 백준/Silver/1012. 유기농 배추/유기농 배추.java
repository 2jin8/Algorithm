import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    private static boolean[][] visited;
    private static int[][] land;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int r = 0; r < t; r++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            init(m, n);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                land[b][a] = 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (land[i][j] == 1 && !visited[i][j]) {
                        DFS(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
            count = 0;
        }

        br.close();
    }

    private static void init(int m, int n) {
        visited = new boolean[n][m];
        land = new int[n][m];
    }

    private static void DFS(int i, int j) {
        visited[i][j] = true;

        // 위(i + 1)
        if (i != land.length - 1) {
            if (land[i + 1][j] == 1 && !visited[i + 1][j]) {
                DFS(i + 1, j);
            }
        }

        // 아래(i - 1)
        if (i != 0) {
            if (land[i - 1][j] == 1 && !visited[i - 1][j]) {
                DFS(i - 1, j);
            }
        }
        // 오른쪽(j + 1)
        if (j != land[0].length - 1) {
            if (land[i][j + 1] == 1 && !visited[i][j + 1]) {
                DFS(i, j + 1);
            }
        }

        // 왼쪽(j - 1)
        if (j != 0) {
            if (land[i][j - 1] == 1 && !visited[i][j - 1]) {
                DFS(i, j - 1);
            }
        }
    }
}