import java.util.*;
import java.io.*;

public class Main {

    private static int N, K;
    private static int[] pointX = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        pointX[N] = 1;
        queue.add(N);

        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (n == K) {
                return pointX[n] - 1;
            }
            if (n - 1 >= 0 && pointX[n - 1] == 0) { // n - 1 좌표 방문하지 않았을 경우
                queue.add(n - 1);
                pointX[n - 1] = pointX[n] + 1;
            }
            if (n + 1 <= 100000 && pointX[n + 1] == 0) { // n + 1 좌표 방문하지 않았을 경우
                queue.add(n + 1);
                pointX[n + 1] = pointX[n] + 1;
            }
            if (2 * n <= 100000 && pointX[2 * n] == 0) { // 2 * n 좌표 방문하지 않았을 경우
                queue.add(2 * n);
                pointX[2 * n] = pointX[n] + 1;
            }
        }
        return -1;
    }
}