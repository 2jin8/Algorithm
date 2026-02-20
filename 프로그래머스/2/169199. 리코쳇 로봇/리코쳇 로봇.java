import java.util.*;

class Solution {
    // . 빈공간 R 처음 위치 D 장애물 위치 G 목표 지점
    
    static int N, M;
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        visited = new boolean[N][M];
        map = new char[N][M];
        
        int sX = 0, sY = 0;
        for (int i=0; i<N; i++) {
            String str = board[i];
            for (int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    sX = i;
                    sY = j;
                }
            }
        }
        
        return bfs(sX, sY);
    }
    
    static int bfs(int sX, int sY) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sX, sY, 0));
        visited[sX][sY] = true;
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (map[now.x][now.y] == 'G')
                return now.d;
            
            for (int d=0; d<4; d++) {
                int x = now.x, y = now.y;
                while (true) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    // 벽이나 장애물에 부딪히면 이동 끝
                    if (nx<0||ny<0||nx>=N||ny>=M||map[nx][ny] == 'D') {
                        // 방문하지 않은 곳이면 방문처리 & 큐에 넣기
                        if (!visited[x][y]) {
                            queue.offer(new Node(x, y, now.d + 1));
                            visited[x][y] = true;
                        }
                        break;
                    }
                    x = nx; y = ny;
                }
            }
        }
        return -1;
    }
    
    static class Node {
        int x, y, d;
        
        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}