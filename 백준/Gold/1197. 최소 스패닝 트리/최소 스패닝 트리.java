import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int v, e;
    private static int[] parent;
    private static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = -1;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            priorityQueue.add(new Edge(u, v, weight));
        }

        long sum = 0;
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int u = edge.u;
            int v = edge.v;
            int root_u = find(u);
            int root_v = find(v);

            if (root_u != root_v) {
                sum += edge.weight;
                union(root_u, root_v);
            }
        }
        System.out.println(sum);
    }

    private static int find(int i) {
        int root, trail, lead;
        for (root = i; parent[root] >= 0; root = parent[root]);
        for (trail = i; trail != root; trail = lead) {
            lead = parent[trail];
            parent[trail] = root;
        }

        return root;
    }

    private static void union(int i, int j) {
        int tmp = parent[i] + parent[j];
        if (-parent[i] < -parent[j]) {
            parent[i] = j;
            parent[j] = tmp;
        } else {
            parent[j] = i;
            parent[i] = tmp;
        }
    }

    public static class Edge {
        int u;
        int v;
        int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}

class EdgeComparator implements Comparator<Main.Edge> {
    @Override
    public int compare(Main.Edge o1, Main.Edge o2) {
        if (o1.weight > o2.weight) return 1;
        else if (o1.weight == o2.weight) return 0;
        else return -1;
    }
}