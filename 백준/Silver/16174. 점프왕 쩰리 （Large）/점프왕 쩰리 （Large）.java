import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    public static String bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[] dx = {1, 0};
        int[] dy = {0, 1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            if (x == N - 1 && y == N - 1) {
                return "HaruHaru";
            }

            // (3, 1)에서 아래로 2칸 이동 → (3 + 1 * 2, 1 + 0 * 2) → (5, 1) → 범위 초과
            // (3, 1)에서 오른쪽으로 2칸 이동 → (3 + 0 * 2, 1 + 1 * 2) → (3, 3)
            for (int i = 0; i < 2; i++) {
                int tx = x + dx[i] * map[x][y];
                int ty = y + dy[i] * map[x][y];
                if (tx < 0 || ty < 0 || tx >= N || ty >= N)
                    continue;
                if (!visited[tx][ty]) {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                }
            }
        }
        return "Hing";
    }
}