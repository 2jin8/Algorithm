import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] room;
    static boolean[] visited;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        room = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            room[a][b] = c;
            room[b][a] = c;
        }
        System.out.println(bfs());
    }

    public static long bfs() {
        Queue<Integer> queue = new LinkedList<>();
        dist[0] = 0; dist[1] = 0;
        visited[1] = true;
        queue.offer(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (room[now][i] != 0 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    dist[i] = dist[now] + room[now][i];
                }
            }
        }
        return Arrays.stream(dist).max().getAsLong();
    }
}