import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V;
    static ArrayList<Integer>[] points;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        // ArrayList 초기화
        points = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            points[i] = new ArrayList<>();
        }

        // 연결된 두 정점의 번호 기록하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방향 간선
            points[u].add(v);
            points[v].add(u);
        }

        // 방문할 정점이 여러 개인 경우, 정점 번호가 작은 것을 먼저 방문 ⇒ 오름차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(points[i]);
        }
        // DFS 탐색
        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");
        // BFS 탐색
        visited = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");

            // 현재 정점과 연결된 모든 정점 확인하기
            for (int i = 0; i < points[now].size(); i++) {
                int next = points[now].get(i);
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

    static void dfs(int v) {
        sb.append(v).append(" ");
        visited[v] = true;

        for (int i = 0; i < points[v].size(); i++) {
            int next = points[v].get(i);
            if (!visited[next]) { // 방문하지 않았다면 DFS 탐색
                dfs(next);
            }
        }
    }
}