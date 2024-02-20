import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        int min = Integer.MAX_VALUE, minIdx = -1;
        for (int i = 1; i <= N; i++) {
            dist = new int[N + 1];
            visited = new boolean[N + 1];
            int ans = bfs(i);
            if (ans < min) {
                min = ans;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
    }

    public static int bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < list[now].size(); i++) {
                int next = list[now].get(i);
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    dist[next] = dist[now] + 1;
                }
            }
        }
        return Arrays.stream(dist).sum();
    }
}