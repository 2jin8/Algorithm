import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static final int MAX = 500000;
    static boolean[][] visited = new boolean[MAX + 1][2];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N][0] = true; // 0: 짝수초, 1: 홀수초

        int time = 0;
        while (!queue.isEmpty()) {
            if (K > MAX) { // 500,000을 넘는 경우
                return -1;
            }

            if (visited[K][time % 2]) { // 동생이 위치한 곳을 방문할 수 있는 경우
                return time;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int x = queue.poll();

                int nextTime = (time + 1) % 2; // 짝수초, 홀수초 구분을 위해
                int next = x - 1;
                if (next >= 0 && !visited[next][nextTime]) {
                    queue.offer(next);
                    visited[next][nextTime] = true;
                }

                next = x + 1;
                if (next <= MAX && !visited[next][nextTime]) {
                    queue.offer(next);
                    visited[next][nextTime] = true;
                }

                next = 2 * x;
                if (next <= MAX && !visited[next][nextTime]) {
                    queue.offer(next);
                    visited[next][nextTime] = true;
                }
            }
            K += ++time;
        }
        return -1;
    }
}