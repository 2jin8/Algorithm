import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int sheep = 0, fox = 0;
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        // 각 영역의 양과 늑대 수 구하고 적은 쪽의 수를 총 수에서 빼면 될 듯하다!
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'v') fox++;
                else if (map[i][j] == 'k') sheep++;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] != '#') {
                    bfs(i, j);
                }
            }
        }
        System.out.println(sheep + " " + fox);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int v = 0, k = 0; // v: 늑대, k: 양
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0];
            y = poll[1];
            if (map[x][y] == 'v') v++;
            else if (map[x][y] == 'k') k++;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= R || ty >= C)
                    continue;

                if (!visited[tx][ty] && map[tx][ty] != '#') {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                }
            }
        }
        if (v >= k) { // 늑대의 수가 더 많거나 같다면
            sheep -= k; // 양의 수 감소
        } else { // 양의 수가 더 많다면
            fox -= v; // 늑대 수 감소
        }
    }
}