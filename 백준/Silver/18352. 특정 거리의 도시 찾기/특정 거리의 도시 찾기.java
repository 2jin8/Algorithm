import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static int[] d;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        d = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        Arrays.fill(d, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        findRoute(X);

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (d[i] == K) {
                cnt++;
                sb.append(i).append("\n");
            }
        }
        System.out.println(cnt == 0 ? "-1" : sb);
    }

    static void findRoute(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int distance = node.distance;
            int idx = node.idx;
            // 현재 distance보다 기록된 distance가 더 짧으면 이미 방문한 정점인 것
            if (d[idx] < distance) continue;
            for (int i = 0; i < graph.get(idx).size(); i++) {
                // idx를 거쳐서 i까지 가는 것 vs i까지 한번에 가는 것
                if (d[idx] + 1 < d[graph.get(idx).get(i)]) {
                    d[graph.get(idx).get(i)] = d[idx] + 1;
                    pq.offer(new Node(graph.get(idx).get(i), d[idx] + 1));
                }
            }
        }
    }
    static class Node implements Comparable<Node> {
        private int idx;
        private int distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }
}