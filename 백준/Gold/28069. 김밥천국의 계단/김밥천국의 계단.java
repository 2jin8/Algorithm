import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        System.out.println(bfs());
    }
    
    public static String bfs() {
        Queue<Stair> queue = new LinkedList<>();
        queue.offer(new Stair(0, 0));
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            Stair now = queue.poll();
            if (now.num == N && now.cnt <= K) {
                return "minigimbob";
            }
            
            if (now.cnt > K) break;
            
            // 한 칸 올라가기
            int next = now.num + 1;
            if (next <= N && !visited[next]) {
                queue.offer(new Stair(next, now.cnt + 1));
                visited[next] = true;
            }
            
            // 순간 이동하기
            next = now.num + now.num / 2;
            if (next <= N && !visited[next]) {
                queue.offer(new Stair(next, now.cnt + 1));
                visited[next] = true;
            }
        }
        return "water";
    }
    
    static class Stair {
        int num; // 계단의 번호
        int cnt; // 해당 계단까지 올라가는 횟수
        
        public Stair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}