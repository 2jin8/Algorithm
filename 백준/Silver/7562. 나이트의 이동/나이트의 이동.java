import java.io.*;
import java.util.*;

public class Main {
    static int l;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            l = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine(), " ");
            Pos end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map = new int[l][l];
            sb.append(bfs(start, end)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int bfs(Pos start, Pos end) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(start);
        map[start.x][start.y] = 1;

        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2}; // 8방향 탐색
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            if (pos.x == end.x && pos.y == end.y) {
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= l || ny >= l) continue;

                if (map[nx][ny] == 0) {
                    queue.offer(new Pos(nx, ny));
                    map[nx][ny] = map[pos.x][pos.y] + 1;
                }
            }
        }
        return map[end.x][end.y] - 1;
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}