import java.io.*;
import java.util.*;

public class Main {
    static int L, R, C;
    static char[][][] building;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String[] s = br.readLine().split(" ");
            L = Integer.parseInt(s[0]);
            R = Integer.parseInt(s[1]);
            C = Integer.parseInt(s[2]);
            if (L == 0 && R == 0 && C == 0) break;

            building = new char[R][C][L];
            visited = new boolean[R][C][L];
            Point start = null;
            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < C; j++) {
                        building[i][j][k] = str.charAt(j);
                        if (building[i][j][k] == 'S') start = new Point(i, j, k, 0);
                    }
                }
                br.readLine();
            }
            int bfs = bfs(start);
            sb.append(bfs == -1 ? "Trapped!" : "Escaped in " + bfs + " minute(s).").append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y][start.z] = true;

        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (building[point.x][point.y][point.z] == 'E')
                return point.time;

            for (int i = 0; i < 6; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                int tz = point.z + dz[i];
                if (tx < 0 || ty < 0 || tz < 0 || tx >= R || ty >= C || tz >= L)
                    continue;

                if (building[tx][ty][tz] != '#' && !visited[tx][ty][tz]) {
                    queue.offer(new Point(tx, ty, tz, point.time + 1));
                    visited[tx][ty][tz] = true;
                }
            }
        }
        return -1;
    }

    static class Point {
        int x;
        int y;
        int z;
        int time; // 탈출 소요 시간

        public Point(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}