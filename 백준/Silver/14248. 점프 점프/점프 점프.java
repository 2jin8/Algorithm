import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] jump;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        jump = new int[n];
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            jump[i] = Integer.parseInt(st.nextToken());
        }
        int start = Integer.parseInt(br.readLine());
        System.out.println(bfs(start - 1));
    }

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            cnt++;

            // 오른쪽으로
            int nx = x + jump[x];
            if (nx < n && !visited[nx]) {
                queue.offer(nx);
                visited[nx] = true;
            }

            nx = x - jump[x];
            if (nx >= 0 && !visited[nx]) {
                queue.offer(nx);
                visited[nx] = true;
            }
        }
        return cnt;
    }
}