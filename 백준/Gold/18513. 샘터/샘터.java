import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static Queue<Pos> queue = new LinkedList<>();
    static HashSet<Integer> visited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            queue.offer(new Pos(idx, 0));
            visited.add(idx);
        }
        bfs();
    }

    public static void bfs() {
        int home = 0;
        long total = 0;

        while (!queue.isEmpty()) {
            if (home == K) { // K개의 집을 세운 경우
                System.out.println(total);
                break;
            }

            Pos pos = queue.poll();
            if (pos.dist != 0) { // 집을 세운 경우, 샘터에서 집까지의 최소 거리 더하기
                total += pos.dist;
                home++;
            }

            if (!visited.contains(pos.idx + 1)) { // 샘터 기준 오른쪽
                queue.offer(new Pos(pos.idx + 1, pos.dist + 1));
                visited.add(pos.idx + 1);
            }

            if (!visited.contains(pos.idx - 1)) { // 샘터 기준 왼쪽
                queue.offer(new Pos(pos.idx - 1, pos.dist + 1));
                visited.add(pos.idx - 1);
            }
        }
    }

    static class Pos {
        int idx;
        long dist;

        public Pos(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
}