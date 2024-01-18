import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        F = Integer.parseInt(str[0]); // 총 F층
        S = Integer.parseInt(str[1]); // 현재 S층
        G = Integer.parseInt(str[2]); // 스타트링크 G층
        U = Integer.parseInt(str[3]); // U만큼 업
        D = Integer.parseInt(str[4]); // D만큼 다운
        dist = new int[F + 1];
        visited = new boolean[F + 1];

        int bfs = bfs(S);
        System.out.println(bfs == -1? "use the stairs" : bfs);;
    }

    public static int bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;

        while (!queue.isEmpty()) {
            x = queue.poll();
            if (x == G) return dist[x];

            if (x + U <= F && !visited[x + U]) {
                dist[x + U] = dist[x] + 1;
                visited[x + U] = true;
                queue.offer(x + U);
            }
            if (x - D > 0 && !visited[x - D]) {
                dist[x - D] = dist[x] + 1;
                visited[x - D] = true;
                queue.offer(x - D);
            }
        }
        return -1;
    }
}