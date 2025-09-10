import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map, minDist;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        minDist = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
        }

        System.out.println(dijkstra(0, 0));
    }

    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    static int dijkstra(int x, int y) {
        // 벽을 부순 개수를 기준으로 우선순위 큐 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.crash, n2.crash));
        pq.offer(new Node(x, y, 0));
        minDist[x][y] = 0;

        int crash = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            // 도착 지점에 도달한 경우
            if (now.x == N - 1 && now.y == M - 1) {
                crash = now.crash;
                break;
            }

            if (visited[now.x][now.y]) continue;
            visited[now.x][now.y] = true;

            // 주변 네 칸으로 이동 가능
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
                    continue;

                // 더 짧은 최단거리(부순 벽의 개수)가 존재하는 경우 업데이트 후 PQ에 넣기
                if (minDist[now.x][now.y] + map[nx][ny] < minDist[nx][ny]) {
                    minDist[nx][ny] = minDist[now.x][now.y] + map[nx][ny];
                    pq.offer(new Node(nx, ny, minDist[nx][ny]));
                }
            }
        }
        return crash;
    }

    static class Node {
        int x, y, crash; // crash: 부순 벽의 개수

        public Node(int x, int y, int crash) {
            this.x = x;
            this.y = y;
            this.crash = crash;
        }
    }
}
