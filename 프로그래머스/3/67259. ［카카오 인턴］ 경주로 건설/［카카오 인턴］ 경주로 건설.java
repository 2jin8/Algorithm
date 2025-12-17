import java.util.*;

class Solution {
    
    // 방향에 따라 달라지니까 3차원 배열을 써야할 듯
    static int N, answer;
    static final int INF = Integer.MAX_VALUE;
    static int[][][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public int solution(int[][] board) {
        answer = INF;
        N = board.length;
        visited = new int[N][N][4]; 
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(visited[i][j], INF);
            }
        }
        answer = Math.min(answer, dfs(board, 0, 0, 1));
        
        visited = new int[N][N][4];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(visited[i][j], INF);
            }
        }
        answer = Math.min(answer, dfs(board, 0, 0, 2));

        return answer;
    }
    
    static int dfs(int[][] map, int x, int y, int dir) {
        PriorityQueue<Road> pq = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.cost, r2.cost));
        pq.offer(new Road(x, y, dir, 0));
        visited[x][y][dir] = 0;
        
        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (now.x == N-1 && now.y == N-1)
                return now.cost;
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1) // 범위 벗어남
                    continue;
                
                int newCost = now.cost + (i == now.dir? 100 : 600);
                if (visited[nx][ny][i] > newCost) {
                    pq.offer(new Road(nx, ny, i, newCost));
                    visited[nx][ny][i] = newCost;
                }

            }
        }
        return INF;
    }
    
    static class Road {
        int x, y, dir, cost;
        
        public Road(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}