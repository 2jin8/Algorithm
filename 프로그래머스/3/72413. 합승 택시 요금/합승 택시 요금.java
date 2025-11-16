import java.util.*;
// n > k + k > a + k > b (k 지점까지 같이 합승, 이후 따로)
// n > a + n > b (그냥 처음부터 따로)
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], 1_000_000);
            dist[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            int u = fare[0], v = fare[1], d = fare[2];
            dist[u][v] = d;
            dist[v][u] = d;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int minDist = dist[s][a] + dist[s][b];
        for (int k = 1; k <= n; k++) {
            minDist = Math.min(minDist, dist[s][k] + dist[k][a] + dist[k][b]);
        }
        return minDist;
    }
}