import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r;
    static final int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // 지역의 개수
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 길의 개수
        int[] t = new int[n]; // 각 구역에 있는 아이템 수
        int[][] dist = new int[n][n]; // 각 지점까지의 거리
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            dist[a][b] = l;
            dist[b][a] = l;
        }
        System.out.println(findDist(dist, t));
    }

    public static int findDist(int[][] dist, int[] t) {
        // 플로이드 와셜 알고리즘
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int total = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= m) total += t[j];
            }
            ans = Math.max(ans, total);
        }
        return ans;
    }
}