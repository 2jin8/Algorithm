import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 연결된 간선 정보 저장
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }

        // 거리 기준 간선 정렬
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1.dist, e2.dist));

        // 자기 자신을 부모로 초기 설정
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        int edgeCount = 0, answer = 0, maxDist = 0;
        for (int i = 0; i < M; i++) {
            Edge edge = edges[i];
            // 사이클이 발생하지 않으면 간선 연결하기
            if (union(edge.a, edge.b)) {
                answer += edge.dist;
                maxDist = Math.max(maxDist, edge.dist);

                // N-1개 간선 모두 연결하면 종료
                if (++edgeCount == N - 1) {
                    break;
                }
            }
        }
        // 두 개의 마을로 분할하기 위해 MST에서 가장 큰 거리를 가지는 간선 제거
        System.out.println(answer- maxDist); 
    }

    static int findRoot(int a) {
        if (parents[a] == a)
            return a;

        return parents[a] = findRoot(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }

    static class Edge {
        int a, b, dist;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }
}
