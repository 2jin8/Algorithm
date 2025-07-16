import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X, INF = 1_000_000_000;
    static ArrayList<City>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[u].add(new City(v, t)); // 단방향
        }

        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            // 집 > X
            Arrays.fill(dist, INF);
            Arrays.fill(visited, false);
            int go = dijkstra(i, X);

            // X > 집
            Arrays.fill(dist, INF);
            Arrays.fill(visited, false);
            int back = dijkstra(X, i);
            maxDist = Math.max(maxDist, go + back);
        }
        System.out.println(maxDist);
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.t, c2.t));
        pq.offer(new City(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            City now = pq.poll();
            if (now.u == end)
                break;

            // 방문 처리
            if (visited[now.u]) continue;
            visited[now.u] = true;

            for (City next : graph[now.u]) {
                if (visited[next.u]) continue;

                if (dist[next.u] > dist[now.u] + next.t) {
                    dist[next.u] = dist[now.u] + next.t;
                    pq.offer(new City(next.u, dist[next.u]));
                }
            }
        }
        return dist[end];
    }

    static class City {
        int u, t;

        public City(int u, int t) {
            this.u = u;
            this.t = t;
        }
    }
}
