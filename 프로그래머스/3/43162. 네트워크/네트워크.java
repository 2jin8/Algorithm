import java.util.*;

class Solution {
    
    // 각 컴퓨터에서 BFS 탐색 (+방문 체크) > bfs 탐색 횟수 return
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;
        for (int i=0; i<n; i++) {
            if (visited[i]) continue;
            
            answer++;
            bfs(computers, i);
        }
        return answer;
    }
    
    static void bfs(int[][] computers, int computer) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(computer);
        visited[computer] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next=0; next<computers.length; next++) {
                if (!visited[next] && computers[now][next] == 1) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}