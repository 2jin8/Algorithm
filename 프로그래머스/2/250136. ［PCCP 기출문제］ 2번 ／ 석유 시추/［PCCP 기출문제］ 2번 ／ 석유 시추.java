import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static HashMap<Integer, Integer> sizeMap = new HashMap<>();
    static int[][] numbers;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        numbers = new int[N][M]; // 석유 덩어리 번호
        visited = new boolean[N][M];
        
        int number = 1;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    bfs(land, i, j, number++);
                }
            }
        }
        
        int answer = 0;
        boolean[] checked = new boolean[number];
        for (int j=0; j<M; j++) {
            Arrays.fill(checked, false);
            int total = 0;
            for (int i=0; i<N; i++) {
                int num = numbers[i][j];
                // 석유가 있고 해당 번호 아직 더하지 않은 경우
                if (land[i][j] == 1 && !checked[num]) {
                    total += sizeMap.get(num);
                    checked[num] = true;
                }
            }
            answer = Math.max(answer, total);
        }
        
        return answer;
    }
    
    public static void bfs(int[][] land, int x, int y, int number) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        
        int size = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            numbers[now[0]][now[1]] = number;
            size++;
            
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
                    continue;

                if (land[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        sizeMap.put(number, size);
    }
}

class Oil {
    int idx, value;
    
    public Oil(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}