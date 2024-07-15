import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int A, B, N, M;
    static int[] dist;
    static Queue<Integer> queue = new LinkedList<>();
    static final int MIN = 0, MAX = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[MAX + 1];


        System.out.println(bfs(N));;
    }

    static int bfs(int x) {
        queue.offer(x);
        dist[x] = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == M) break;

            // +1칸, -1칸
            movePlus(now, now + 1);
            moveMinus(now, now - 1);

            // A나 B만큼 좌우로 점프
            movePlus(now, now + A);
            movePlus(now, now * A);
            movePlus(now, now + B);
            movePlus(now, now * B);

            // A배나 B배의 위치로 이동
            moveMinus(now, now - A);
            moveMinus(now, now - B);
        }

        return dist[M] - 1;
    }

    static void movePlus(int now, int next) {
        if (next <= MAX && dist[next] == 0) {
            queue.offer(next);
            dist[next] = dist[now] + 1;
        }
    }

    static void moveMinus(int now, int next) {
        if (next >= MIN && dist[next] == 0) {
            queue.offer(next);
            dist[next] = dist[now] + 1;
        }
    }
}