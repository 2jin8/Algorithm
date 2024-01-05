import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][] route;
    static char[][] building;
    static boolean[][] visited;
    static Queue<Point> fireQ, personQ;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            init();
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    building[i][j] = line.charAt(j);
                    if (building[i][j] == '*') {
                        fireQ.add(new Point(i, j));
                        visited[i][j] = true;
                    } else if (building[i][j] == '@') {
                        personQ.add(new Point(i, j));
                        route[i][j] = 1;
                    }
                }
            }
            int result = bfs();
            sb.append(result == -1 ? "IMPOSSIBLE" : result).append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!personQ.isEmpty()) {
            int size = fireQ.size();
            for (int i = 0; i < size; i++) {
                Point fire = fireQ.poll();

                for (int j = 0; j < 4; j++) {
                    int tx = fire.x + dx[j];
                    int ty = fire.y + dy[j];
                    if (tx < 0 || ty < 0 || tx >= h || ty >= w)
                        continue;

                    // 벽이 아니고 아직 불이 번지지 않은 곳인 경우
                    if (building[tx][ty] != '#' && !visited[tx][ty]) {
                        fireQ.offer(new Point(tx, ty));
                        visited[tx][ty] = true;
                    }
                }
            }

            size = personQ.size();
            for (int i = 0; i < size; i++) {
                Point person = personQ.poll();
                int x = person.x;
                int y = person.y;
                if (x == 0 || x == h - 1 || y == 0 || y == w - 1) {
                    return route[x][y];
                }

                for (int j = 0; j < 4; j++) {
                    int tx = x + dx[j];
                    int ty = y + dy[j];
                    if (tx < 0 || ty < 0 || tx >= h || ty >= w)
                        continue;

                    // 벽이 아니고 불이 번지지 않았고 방문하지 않은 곳인 경우
                    if (building[tx][ty] != '#' && !visited[tx][ty] && route[tx][ty] == 0) {
                        route[tx][ty] = route[x][y] + 1;
                        personQ.offer(new Point(tx, ty));
                        visited[tx][ty] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void init() {
        route = new int[h][w];
        building = new char[h][w];
        visited = new boolean[h][w];
        fireQ = new LinkedList<>();
        personQ = new LinkedList<>();
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}