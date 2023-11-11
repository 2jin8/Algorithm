import java.util.*;
import java.io.*;

public class Main {
    static int r, c;
    static char[][] forest;
    static boolean[][] full; // 물 방문 여부 기록
    static int[][] time; // 이동할 수 있는 시간 기록
    static Queue<Point> water = new LinkedList<>(); // 물 이동 가능한 위치
    static Queue<Point> animal = new LinkedList<>(); // 도치 이동 가능한 위치
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        forest = new char[r][c];
        full = new boolean[r][c];
        time = new int[r][c];
        Point start = null, end = null;
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                forest[i][j] = str.charAt(j);
                if (forest[i][j] == 'S') start = new Point(i, j);
                else if (forest[i][j] == 'D') end = new Point(i, j);
                else if (forest[i][j] == '*') {
                    water.offer(new Point(i, j));
                    full[i][j] = true;
                }
            }
        }
        int bfs = bfs(start, end);
        if (bfs == -1) System.out.println("KAKTUS");
        else System.out.println(bfs);
    }

    public static int bfs(Point start, Point end) {
        animal.offer(start);
        time[start.x][start.y] = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!animal.isEmpty()) {
            // 물 이동
            int water_size = water.size();
            for (int i = 0; i < water_size; i++) {
                Point point = water.poll();

                for (int j = 0; j < 4; j++) {
                    int tx = point.x + dx[j];
                    int ty = point.y + dy[j];

                    if (tx < 0 || ty < 0 || tx >= r || ty >= c) // 범위 벗어나면 이동 불가
                        continue;

                    if (forest[tx][ty] == 'X' || forest[tx][ty] == 'D') // 벽이거나 굴이면 이동 불가
                        continue;

                    if (!full[tx][ty]) {
                        water.offer(new Point(tx, ty));
                        full[tx][ty] = true;
                    }
                }
            }

            // 도치 이동
            int animal_size = animal.size();
            for (int i = 0; i < animal_size; i++) {
                Point point = animal.poll();
                if (point.x == end.x && point.y == end.y)
                    return time[point.x][point.y] - 1;

                for (int j = 0; j < 4; j++) {
                    int tx = point.x + dx[j];
                    int ty = point.y + dy[j];
                    if (tx < 0 || ty < 0 || tx >= r || ty >= c) // 범위 벗어나면 이동 불가
                        continue;

                    if (forest[tx][ty] == 'X' || full[tx][ty]) // 벽이거나 물이 찼으면 이동 불가
                        continue;

                    if ((forest[tx][ty] == '.' || forest[tx][ty] == 'D') && time[tx][ty] == 0) {
                        animal.offer(new Point(tx, ty));
                        time[tx][ty] = time[point.x][point.y] + 1;
                    }
                }
            }
        }
        return -1;
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