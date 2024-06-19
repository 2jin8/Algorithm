import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;
    static int[] building;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        F = Integer.parseInt(st.nextToken()); // 총 F층
        S = Integer.parseInt(st.nextToken()); // 현재 강호가 있는 층
        G = Integer.parseInt(st.nextToken()); // 스타트링크가 있는 층
        U = Integer.parseInt(st.nextToken()); // 위로 이동하는 층 수
        D = Integer.parseInt(st.nextToken()); // 아래로 이동하는 층 수

        building = new int[F + 1]; // F층 건물
        int bfs = bfs(S);
        System.out.println(bfs == -1 ? "use the stairs" : bfs);
    }

    public static int bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        building[x] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == G) {
                return building[G] - 1;
            }

            // U만큼 위로 이동
            int next = now + U;
            if (next <= F && building[next] == 0) {
                queue.offer(next);
                building[next] = building[now] + 1;
            }

            // D만큼 아래로 이동
            next = now - D;
            if (next >= 1 && building[next] == 0) {
                queue.offer(next);
                building[next] = building[now] + 1;
            }
        }
        return -1;
    }
}