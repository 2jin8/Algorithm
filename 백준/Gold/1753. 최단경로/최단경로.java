import java.io.*;
import java.util.*;

public class Main {
    static int V, E, K;
    static int[] d;
    static final int INF = (int) 1e9;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken()); // 1부터 시작
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine()) - 1; // 시작 정점
        d = new int[V];
        Arrays.fill(d, INF);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }
        findRoute(K);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            if (d[i] == INF) sb.append("INF\n");
            else sb.append(d[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void findRoute(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.idx;

            // 기록된 최단 경로가 dist보다 짧다 → 최단 경로로 이미 갱신된 것
            if (d[now] < node.dist) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i).idx;
                int cost = d[now] + graph.get(now).get(i).dist;
                if (cost < d[next]) {
                    d[next] = cost;
                    pq.offer(new Node(next, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        private int idx;
        private int dist;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
}