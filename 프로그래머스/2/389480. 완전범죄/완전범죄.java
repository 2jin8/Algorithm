import java.util.*;

class Solution {
    
    static final int INF = 1_000_000_000;
    
    public int solution(int[][] info, int n, int m) {
        int count = info.length; // 물건의 개수
        // dp[i][b]: i번 물건까지 고려 & B의 흔적이 b일 때 A의 흔적의 최솟값
        int[][] dp = new int[count + 1][m];
        for (int i=0; i<=count; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0; // 초기값
        for (int i=1; i<=count; i++) { // 물건 1번 ~ count번
            int a = info[i - 1][0];
            int b = info[i - 1][1];
            
            // 
            for (int j=0; j<m; j++) {
                // A 선택한 경우
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                
                // B 선택한 경우
                if (j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }
        
        int answer = INF;
        for (int i=0; i<m; i++) {
            answer = Math.min(answer, dp[count][i]);
        }
        return answer >= n? -1 : answer;
    }
}
