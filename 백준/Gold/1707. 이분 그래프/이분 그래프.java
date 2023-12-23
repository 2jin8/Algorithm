import java.io.*;
import java.util.*;

public class Main {
    private static int V, E, check;
    private static int[] colors; // 1: 빨강, 2: 파랑
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 초기화
            colors = new int[V + 1];
            visited = new boolean[V + 1];
            graph = new ArrayList[V + 1];
            for (int j = 0; j <= V; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            // bfs 탐색 시작
            check = 0; // 0: 이분 그래프 O, 1: 이분 그래프 X
            for (int j = 1; j <= V; j++) {
                if (!visited[j]) bfs(j);
                if (check == 1) break;
            }
            bw.write((check == 1) ? "NO\n" : "YES\n");
        }
        bw.flush(); bw.close();
    }

    public static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        colors[v] = 1;

        while (!queue.isEmpty()) {
            v = queue.poll();
            int color = colors[v];

            for (int i = 0; i < graph[v].size(); i++) {
                int u = graph[v].get(i);
                if (!visited[u]) { // 방문 X -> 방문 처리 & 다른 색으로 칠하기
                    visited[u] = true;
                    colors[u] = (color == 1) ? 2 : 1;
                    queue.offer(u);
                } else if (visited[u] && colors[u] == color) { // 이미 방문 & 색이 같다 -> 이분 그래프 X
                    check = 1;
                    return;
                }
            }
        }
    }
}