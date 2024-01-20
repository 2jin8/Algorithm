import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 오른쪽과 아래로만 이동 가능
        // 이동할 수 있는 칸의 수는 현재 밟고 있는 칸에 쓰여있는 수 만큼
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        System.out.println(bfs(0, 0));
    }

    public static String bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            x = poll[0];
            y = poll[1];
            if (x == N - 1 && y == N - 1) {
                return "HaruHaru";
            }

            // 칸에 적힌 수 만큼 이동
            int move = map[x][y];
            // 오른쪽으로 이동
            if (y + move < N && !visited[x][y + move]) {
                q.offer(new int[]{x, y + move});
                visited[x][y + move] = true;
            }

            // 아래로 이동
            if (x + move < N && !visited[x + move][y]) {
                q.offer(new int[]{x + move, y});
                visited[x + move][y] = true;
            }
        }
        return "Hing";
    }
}