import java.util.*;
import java.io.*;

public class Main {
    private static int n, m;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방향
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                cnt++;
                bfs(i);
            }
        }
        System.out.println(cnt);
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            v = queue.poll();

            for (int i = 0; i < graph.get(v).size(); i++) {
                int u = graph.get(v).get(i);
                if (!visited[u]) {
                    visited[u] = true;
                    queue.offer(u);
                }
            }
        }
    }
}