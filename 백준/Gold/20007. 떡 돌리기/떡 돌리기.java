import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /**
     * 하루에 X보다 먼 거리를 걷지 않고 가까운 집부터 방문
     * 왕복할 수 업슨 거리는 다음날
     */
    static int N, M, X, Y;
    static int[] dist;
    static ArrayList<Home>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 양방향 연결
            graph[a].add(new Home(b, c));
            graph[b].add(new Home(a, c));
        }

        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        PriorityQueue<Home> pq = new PriorityQueue<>();
        pq.offer(new Home(Y, 0));
        dist[Y] = 0;

        int day = 1, todayDist = 0, visited = 0;
        while (!pq.isEmpty()) {
            Home now = pq.poll();
            // 최단 거리가 아니면 넘어가기
            if (now.d > dist[now.x]) continue;

            // 아직 이동할 수 있는 거리가 남은 경우
            if (todayDist + now.d * 2 <= X) {
                todayDist += now.d * 2;
            }
            // 하루에 이동할 수 있는 거리를 초과했지만 왕복 거리가 X보다 작은 경우
            else if ((todayDist + now.d * 2) > X && (now.d * 2 <= X)) {
                day++;
                todayDist = now.d * 2;
            }
            // 하루에 이동할 수 있는 거리도 초과했고 왕복 거리가 X보다 큰 경우
            else continue;

            visited++;

            for (Home next : graph[now.x]) {
                if (dist[next.x] > dist[now.x] + next.d) {
                    dist[next.x] = dist[now.x] + next.d;
                    pq.offer(new Home(next.x, dist[next.x]));
                }
            }
        }
        return visited == N ? day : -1;
    }

    static class Home implements Comparable<Home> {
        int x, d;

        public Home(int x, int d) {
            this.x = x;
            this.d = d;
        }

        @Override
        public int compareTo(Home home) {
            return Integer.compare(this.d, home.d);
        }
    }
}
