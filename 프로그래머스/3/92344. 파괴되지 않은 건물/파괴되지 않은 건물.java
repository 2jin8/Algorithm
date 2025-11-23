class Solution {
    static int N, M;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        int[][] info = new int[N + 1][M + 1];
        
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = (type == 1? -s[5] : s[5]);
            
            info[r1][c1] += degree;
            info[r1][c2 + 1] -= degree;
            info[r2 + 1][c1] -= degree;
            info[r2 + 1][c2 + 1] += degree;
        }
        
        // 가로
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                info[i][j] += info[i][j - 1];
            }
        }
        
        // 세로
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                info[i][j] += info[i - 1][j];
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + info[i][j] > 0)
                    answer++;
            }
        }
        
        return answer;
    }
}
// type: 1이면 공격, 2면 회복
// r1, c1 ~ r2, c2 degree만큼
