import java.util.*;
import java.io.*;

public class Main {
    private static boolean[] visited;
    private static int[] line, route;
    private static final int INF = 100001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        line = new int[INF];
        route = new int[INF];
        visited = new boolean[INF];
        Arrays.fill(line, INF);

        int time = bfs(n, k); // 찾는 가장 빠른 시간
        int before_idx = k; // 경로 출력을 위한 인덱스
        Stack<Integer> stack = new Stack<>();
        for (int i = time - 1; i >= 0; i--) {
            int before = route[before_idx];
            before_idx = before;
            stack.push(before);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(time).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        sb.append(k);
        System.out.println(sb);
    }

    public static int bfs(int x, int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;
        line[x] = 0;

        while (!queue.isEmpty()) {
            x = queue.poll();
            if (x == k) break;

            int x1 = x - 1, x2 = x + 1, x3 = 2 * x;
            if (x1 >= 0 && !visited[x1]) {
                queue.offer(x1);
                visited[x1] = true;
                line[x1] = Math.min(line[x1], line[x] + 1);
                route[x1] = x;
            }

            if (x2 < INF && !visited[x2]) {
                queue.offer(x2);
                visited[x2] = true;
                line[x2] = Math.min(line[x2], line[x] + 1);
                route[x2] = x;
            }

            if (x3 < INF && !visited[x3]) {
                queue.offer(x3);
                visited[x3] = true;
                line[x3] = Math.min(line[x3], line[x] + 1);
                route[x3] = x;
            }
        }
        return line[k];
    }
}