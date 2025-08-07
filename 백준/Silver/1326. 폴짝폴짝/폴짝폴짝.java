import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M = 10_000, A, B;
    static int[] jump;
    static boolean[][] visited; // 번호 - 거리
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        jump = new int[N + 1];
        visited = new boolean[N + 1][M + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            jump[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(A, 0));
        visited[A][0] = true;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.x == B) {
                return now.dist;
            }

            // 징검다리에 적힌 수의 배수만큼 떨어진 곳으로 갈 수 있음
            // 앞으로 이동
            for (int i = 1; i <= N; i++) {
                int move = now.x + jump[now.x] * i;
                if (move > N) break;

                if (!visited[move][now.dist + 1]) {
                    pq.offer(new Node(move, now.dist + 1));
                    visited[move][now.dist + 1] = true;
                }
            }

            // 뒤로 이동
            for (int i = 1; i <= N; i++) {
                int move = now.x - jump[now.x] * i;
                if (move <= 0) break;

                if (!visited[move][now.dist + 1]) {
                    pq.offer(new Node(move, now.dist + 1));
                    visited[move][now.dist + 1] = true;
                }
            }
        }
        return -1;
    }

    static class Node implements Comparable<Node> {
        int x, dist;

        public Node(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
