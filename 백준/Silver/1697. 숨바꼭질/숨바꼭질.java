import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static final int MAX = 100000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(bfs());
    }

    public static int bfs() {
        int[] point = new int[MAX + 1];
        boolean[] visited = new boolean[MAX + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = true;
        while (!queue.isEmpty()) {
            int p = queue.poll();
            if (p == K) break;

            if (p >= 1 && !visited[p - 1]) {
                queue.offer(p - 1);
                visited[p - 1] = true;
                point[p - 1] = point[p] + 1;
            }
            if (p + 1 <= MAX && !visited[p + 1]) {
                queue.offer(p + 1);
                visited[p + 1] = true;
                point[p + 1] = point[p] + 1;
            }
            if (2 * p <= MAX && !visited[2 * p]) {
                queue.offer(2 * p);
                visited[2 * p] = true;
                point[2 * p] = point[p] + 1;
            }
        }
        return point[K];
    }
}