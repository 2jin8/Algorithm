import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[][] mixed = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            mixed[a][b] = true;
            mixed[b][a] = true;
        }

        int total = 0;
        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (mixed[i][j]) continue;
                for (int k = j + 1; k <= n; k++) {
                    if (mixed[i][k] || mixed[j][k]) continue;
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}