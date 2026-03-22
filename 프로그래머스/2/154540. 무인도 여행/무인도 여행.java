import java.util.*;
class Solution {
    
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx={1, -1, 0, 0}, dy={0, 0, 1, -1};
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            map[i] = maps[i].toCharArray();
        }
        
        ArrayList<Integer> islandList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                // 바다 아니고 방문하지 않은 곳 => 무인도 탐색 시작
                if (map[i][j] != 'X' && !visited[i][j]) {
                    islandList.add(bfs(i, j));
                }
            }
        }
        
        // 지낼 수 있는 무인도가 없는 경우
        if (islandList.isEmpty()) return new int[]{-1};
        
        int[] answer = new int[islandList.size()];
        Collections.sort(islandList); // 오름차순 정렬
        for (int i=0; i<answer.length; i++) {
            answer[i] = islandList.get(i);
        }
        
        return answer;
    }
    
    static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        
        int days = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            days += (int) (map[now[0]][now[1]]-'0');
            
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx<0 || ny<0 || nx>=N || ny>=M)
                    continue;
                
                if (visited[nx][ny] || map[nx][ny] == 'X')
                    continue;
                
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        return days;
    }
}
