import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[MAX + 1];
        dist[N] = 1;
        queue.offer(N);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == K) break;

            // x - 1
            int nx = x - 1;
            if (nx >= 0 && dist[nx] == 0) {
                dist[nx] = dist[x] + 1;
                queue.offer(nx);
            }

            nx = x + 1;
            if (nx <= MAX && dist[nx] == 0) {
                dist[nx] = dist[x] + 1;
                queue.offer(nx);
            }

            nx = 2 * x;
            if (nx <= MAX && dist[nx] == 0) {
                dist[nx] = dist[x] + 1;
                queue.offer(nx);
            }
        }
        return dist[K] - 1;
    }
}