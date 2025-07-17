import java.util.*;

class Solution {
    // BFS로 최단경로 구하는거네
    static int N, M;
    static int[][] dist;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        dist = new int[N][M];
        
        return bfs(maps);
    }
    
    static int bfs(int[][] maps) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1];
            
            // 도착 지점
            if (x == N - 1 && y == M - 1)
                return dist[x][y];
            
            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx<0 || ny<0 || nx>=N || ny>=M)
                    continue;
                
                // 이미 방문 or 벽
                if (dist[nx][ny] != 0 || maps[nx][ny] == 0)
                    continue;
                
                queue.offer(new int[]{nx, ny});
                dist[nx][ny] = dist[x][y] + 1;
            }
        }
        return -1;
    }
}