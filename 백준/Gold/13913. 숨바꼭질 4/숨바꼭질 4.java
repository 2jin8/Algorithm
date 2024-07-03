import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static final int MAX = 100_000;
    static int[] time, record;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = new int[MAX + 1];
        record = new int[MAX + 1];
        record[N] = -1;

        StringBuilder sb = new StringBuilder();
        int dist = bfs();
        int now = K;
        sb.append(now);
        while (true) {
            now = record[now];
            if (now == -1) break;
            sb.insert(0, now + " ");
        }
        sb.insert(0, dist + "\n");
        System.out.println(sb.toString());
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        time[N] = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == K) break;

            int next = now - 1;
            if (next >= 0 && time[next] == 0) {
                queue.offer(next);
                time[next] = time[now] + 1;
                record[next] = now;
            }

            next = now + 1;
            if (next <= MAX && time[next] == 0) {
                queue.offer(next);
                time[next] = time[now] + 1;
                record[next] = now;
            }

            next = now * 2;
            if (next <= MAX && time[next] == 0) {
                queue.offer(next);
                time[next] = time[now] + 1;
                record[next] = now;
            }
        }
        return time[K] - 1;
    }
}