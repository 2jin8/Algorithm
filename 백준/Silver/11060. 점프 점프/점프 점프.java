import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] maze;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maze = new int[N];
        visited = new boolean[N];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            maze[i] = Integer.parseInt(str[i]);
        }

        System.out.println(bfs(0));
    }

    public static int bfs(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        visited[x] = true;
        int[] dist = new int[N];
        while (!q.isEmpty()) {
            x = q.poll();
            if (x == N - 1) {
                return dist[x];
            }

            for (int i = 1; i <= maze[x]; i++) {
                int tx = x + i;
                if (tx >= N) continue;

                if (!visited[tx]) {
                    q.offer(tx);
                    visited[tx] = true;
                    dist[tx] = dist[x] + 1;
                }
            }
        }
        return -1;
    }
}