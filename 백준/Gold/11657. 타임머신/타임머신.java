import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 벨만-포드 알고리즘은 간선을 중심으로 동작
    // 기록된 거리가 무한대가 아닌 것에 대해서만 반복
    // 그래프의 모든 Edge에 대해 반복하면서 최단 경로 값 갱신
    // 정점수 - 1 번의 반복이 끝났는데도 최단 경로 값이 갱신되면 음수 사이클 존재 > 최단거리 X

    static int N, M, INF = 1_000_000_000;
    static long[] dist;
    static ArrayList<Edge>[] edgeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        edgeList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[a].add(new Edge(b, c));
        }

        dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0; // 1번에서 출발

        // N - 1번 반복하기
        for (int i = 0; i < N - 1; i++) {
            checkDist();
        }

        // N번째에 바뀌는 것이 있다면 해당 도시로 가는 경로 없음
        if (checkDist()) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
        }
        System.out.println(sb);
    }

    // 간선 기준 경로 선택
    static boolean checkDist() {
        boolean isChanged = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == INF) continue;

            // 시작 정점: i, 도착 정점: next.vertex
            for (Edge next : edgeList[i]) {
                if (dist[next.v] > dist[i] + next.w) {
                    dist[next.v] = dist[i] + next.w;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
