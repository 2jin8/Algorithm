import java.util.*;
import java.io.*;

public class Main {
    private static int n, m, v;
    private static boolean[] bVisited, dVisited;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수
        v = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        bVisited = new boolean[n + 1];
        dVisited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            // 방문할 수 있는 정점이 여러 개 → 정점 번호가 작은 것부터
            Collections.sort(graph.get(i));
        }

        dfs(v, 0);
        sb.append("\n");
        bfs(v);
        System.out.println(sb);
    }

    private static void dfs(int x, int depth) {
        dVisited[x] = true;
        sb.append(x).append(" ");

        for (int i = 0; i < graph.get(x).size(); i++) {
            int y = graph.get(x).get(i);
            if (!dVisited[y]) dfs(y, depth + 1);
        }
    }

    private static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        bVisited[x] = true;

        while (!queue.isEmpty()) {
            x = queue.poll();
            sb.append(x).append(" ");

            for (int i = 0; i < graph.get(x).size(); i++) {
                int y = graph.get(x).get(i);
                if (!bVisited[y]) {
                    bVisited[y] = true;
                    queue.offer(y);
                }
            }
        }
    }
}