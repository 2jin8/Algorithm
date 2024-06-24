import java.io.*;
import java.util.*;

public class Main {
    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dist = new int[N + 1];

        bfs(N);
        System.out.println(dist[1] - 1);
    }

    static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        dist[x] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == 1) break; // 1을 만들었다면 탐색 종료

            int next = now / 3;
            if (now % 3 == 0 && dist[next] == 0) {
                queue.offer(next);
                dist[next] = dist[now] + 1;
            }

            next = now / 2;
            if (now % 2 == 0 && dist[next] == 0) {
                queue.offer(next);
                dist[next] = dist[now] + 1;
            }

            next = now - 1;
            if (dist[next] == 0) {
                queue.offer(next);
                dist[next] = dist[now] + 1;
            }
        }
    }
}