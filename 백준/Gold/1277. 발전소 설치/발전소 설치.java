import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, W;
    static double M;
    static ArrayList<Node>[] graph; // 발전소 간 거리
    static int[][] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 발전소의 수
        W = Integer.parseInt(st.nextToken()); // 남아있는 전선의 수
        M = Double.parseDouble(br.readLine()); // 제한 길이

        // 발전소 좌표 저장
        points = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i][0] = x;
            points[i][1] = y;
        }

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 모든 가능한 연결 추가
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dist = getDistance(i, j);

                // 연결 가능한 전선 정보만 저장하기
                if (dist <= M) {
                    graph[i].add(new Node(j, dist));
                    graph[j].add(new Node(i, dist));
                }
            }
        }

        // 이미 연결된 전선 정보 (거리 0으로 추가)
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, 0));
            graph[b].add(new Node(a, 0));
        }

        // 다익스트라로 최단 경로 계산
        double result = dijkstra(1, N);
        System.out.println((int)(result * 1000));
    }

    static double getDistance(int a, int b) {
        long dx = points[a][0] - points[b][0];
        long dy = points[a][1] - points[b][1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    static double dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        double[] dist = new double[N + 1];

        Arrays.fill(dist, Double.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.idx;

            if (visited[now]) continue;
            visited[now] = true;

            if (now == end) return dist[end];

            for (Node next : graph[now]) {
                if (dist[next.idx] > dist[now] + next.dist) {
                    dist[next.idx] = dist[now] + next.dist;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        return -1;
    }

    static class Node implements Comparable<Node> {
        int idx;
        double dist;

        public Node(int idx, double dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }
}