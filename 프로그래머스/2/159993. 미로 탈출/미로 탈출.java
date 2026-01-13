import java.util.*;

class Solution {
    
    static int N, M, sx, sy, ex, ey;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        visited = new boolean[N][M][2]; // [0]: 레버 X, [1]: 레버 O
        for (int i=0; i<N; i++) {
            char[] chars = maps[i].toCharArray();
            for (int j=0; j<M; j++) {
                map[i][j] = chars[j];
                if (map[i][j] == 'S') {
                    sx = i; sy = j;
                } else if (map[i][j] == 'E') {
                    ex = i; ey = j;
                }
            }
        }
        return bfs();
    }
    
    static int bfs() {
        PriorityQueue<Room> pq = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.dist, r2.dist));
        pq.offer(new Room(sx, sy, 0, 0));
        visited[sx][sy][0] = true;
        
        while (!pq.isEmpty()) {
            Room now = pq.poll();
            if (now.x == ex && now.y == ey && now.lever == 1) 
                return now.dist;
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx<0||ny<0||nx>=N||ny>=M) 
                    continue;
                
                // 레버 획득
                if (map[nx][ny] == 'L' && !visited[nx][ny][1]) {
                    pq.offer(new Room(nx, ny, now.dist + 1, 1));
                    visited[nx][ny][1] = true;
                } 
                // 벽이 아니고 방문하지 않은 곳이면 이동 가능
                else if (map[nx][ny] != 'X' && !visited[nx][ny][now.lever]) {
                    pq.offer(new Room(nx, ny, now.dist + 1, now.lever));
                    visited[nx][ny][now.lever] = true;
                }
            }
        }
        return -1;
    }
    
    static class Room {
        int x, y, dist, lever;
        
        public Room(int x, int y, int dist, int lever) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.lever = lever;
        }
    }
}