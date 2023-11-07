import java.util.*;
import java.io.*;

public class Main {

    static final int INF = (int) 1e9;
    static int v, e, k;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] d = new int[20001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수
        k = Integer.parseInt(br.readLine()); // 시작점

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        Arrays.fill(d, INF);

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        dijkstra();
        for (int i = 1; i <= v; i++) {
            if (d[i] == INF) System.out.println("INF");
            else System.out.println(d[i]);
        }
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(k, 0));
        d[k] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.getDistance();
            int now = node.getIndex();
            if (d[now] < dist) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();
                int idx = graph.get(now).get(i).getIndex();
                if (cost < d[idx]) {
                    d[idx] = cost;
                    pq.offer(new Node(idx, cost));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    private int index;
    private int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node other) {
        if (this.distance < other.distance)
            return -1;
        return 1;
    }
}