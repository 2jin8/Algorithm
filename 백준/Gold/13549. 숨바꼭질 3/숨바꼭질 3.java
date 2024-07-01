import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(bfs(N, K));
    }

    static int bfs(int n, int k) {
        int[] time = new int[MAX + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        time[n] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == k) break;

            // 순간이동은 0초가 걸리므로 가장 먼저 수행하기
            int next = now * 2;
            if (next <= MAX && time[next] == 0) {
                time[next] = time[now];
                queue.offer(next);
            }

            // X-1로 이동하는 것은 1초가 걸림
            next = now - 1;
            if (next >= 0 && time[next] == 0) {
                time[next] = time[now] + 1;
                queue.offer(next);
            }

            // X+1로 이동하는 것은 1초가 걸림
            next = now + 1;
            if (next <= MAX && time[next] == 0) {
                time[next] = time[now] + 1;
                queue.offer(next);
            }
        }
        return time[k] - 1;
    }
}